package com.ritik.timelessTastes_backend.service;

import com.ritik.timelessTastes_backend.model.IngredientCategory;
import com.ritik.timelessTastes_backend.model.IngredientItem;
import com.ritik.timelessTastes_backend.model.Restaurant;
import com.ritik.timelessTastes_backend.repository.IngredientCategoryRepository;
import com.ritik.timelessTastes_backend.repository.IngredientItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsServiceImp implements IngredientsService{

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setName(name);
        ingredientCategory.setRestaurant(restaurant);

        return ingredientCategoryRepository.save(ingredientCategory);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> opt = ingredientCategoryRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("ingredient not found with id: " + id);
        }
        return opt.get();

    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = findIngredientCategoryById(categoryId);

        IngredientItem ingredientItem = new IngredientItem();
        ingredientItem.setName(ingredientName);
        ingredientItem.setRestaurant(restaurant);
        ingredientItem.setCategory(category);

        IngredientItem savedIngredient = ingredientItemRepository.save(ingredientItem);
        category.getIngredients().add(savedIngredient);

        return ingredientItem;
    }

    @Override
    public List<IngredientItem> findRestaurantIngredients(Long restaurantId) throws Exception {
        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientItem updateStock(Long id) throws Exception {
        Optional<IngredientItem> opt = ingredientItemRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("ingredient not found with it: " + id);
        }
        IngredientItem ingredientItem = opt.get();
        ingredientItem.setInStock(!ingredientItem.isInStock());
        return ingredientItemRepository.save(ingredientItem);
    }
}
