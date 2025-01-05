package com.ritik.timelessTastes_backend.controller;

import com.ritik.timelessTastes_backend.model.Cart;
import com.ritik.timelessTastes_backend.model.CartItem;
import com.ritik.timelessTastes_backend.model.User;
import com.ritik.timelessTastes_backend.request.AddCartItemRequest;
import com.ritik.timelessTastes_backend.request.UpdateCartItemRequest;
import com.ritik.timelessTastes_backend.service.CartService;
import com.ritik.timelessTastes_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @PutMapping("/cart/add")
    private ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest req,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        CartItem cartItem = cartService.addItemToCart(req, jwt);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping("/cart-item/update")
    private ResponseEntity<CartItem> updateCartItem(@RequestBody UpdateCartItemRequest req,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        CartItem cartItem = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @DeleteMapping("/cart-item/{id}/remove")
    private ResponseEntity<Cart> removeCartItem(@PathVariable Long id,
                                                @RequestHeader("Authorization") String jwt) throws Exception {
        Cart cart = cartService.removeItemFromCart(id, jwt);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/cart/clear")
    private ResponseEntity<Cart> clearCart(@RequestBody UpdateCartItemRequest req,
                                                    @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.clearCart(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/cart")
    private ResponseEntity<Cart> findUserCart( @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findCartByUserId(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

}
