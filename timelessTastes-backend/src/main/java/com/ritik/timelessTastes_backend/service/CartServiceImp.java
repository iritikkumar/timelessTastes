package com.ritik.timelessTastes_backend.service;

import com.ritik.timelessTastes_backend.model.Cart;
import com.ritik.timelessTastes_backend.model.CartItem;
import com.ritik.timelessTastes_backend.model.Food;
import com.ritik.timelessTastes_backend.model.User;
import com.ritik.timelessTastes_backend.repository.CartItemRepository;
import com.ritik.timelessTastes_backend.repository.CartRepository;
import com.ritik.timelessTastes_backend.repository.FoodRepository;
import com.ritik.timelessTastes_backend.request.AddCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CartServiceImp implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.findFoodById(req.getFoodId());
        Cart cart = cartRepository.findByCustomerId(user.getId());

        for(CartItem cartItem: cart.getItems()){
            if(cartItem.getFood().equals(food)){
                int newQty = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQty);
            }
        }

        CartItem  newCartItem = new CartItem();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setIngredients(req.getIngredients());
        newCartItem.setTotalPrice(req.getQuantity()*food.getPrice());

        CartItem savedItem = cartItemRepository.save(newCartItem);
        cart.getItems().add(savedItem);

        return savedItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()){
            throw new Exception("no cart item with id: " + cartItemId);
        }
        CartItem cartItem = cartItemOptional.get();
        cartItem.setQuantity(quantity);

        cartItem.setTotalPrice(cartItem.getFood().getPrice() * cartItem.getQuantity());

        return cartItemRepository.save(cartItem);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());

        Optional<CartItem> opt = cartItemRepository.findById(cartItemId);

        if(opt.isEmpty()){
            throw new Exception("no cart item with id: " + cartItemId);
        }
        CartItem cartItem = opt.get();

        cart.getItems().remove(cartItem);

        return cartRepository.save(cart);

    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        Long total = 0l;

        for(CartItem cartItem: cart.getItems()){
            total += cartItem.getFood().getPrice() * cartItem.getQuantity();
        }

        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> opt = cartRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("cart now found with id: " + id);
        }
        return opt.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        return cartRepository.findByCustomerId(userId);
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        Cart cart = findCartByUserId(userId);
        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}
