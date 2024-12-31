package com.ritik.timelessTastes_backend.service;

import com.ritik.timelessTastes_backend.model.Category;
import com.ritik.timelessTastes_backend.model.Restaurant;
import com.ritik.timelessTastes_backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long userId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(userId);
        return categoryRepository.findByRestaurantId(restaurant.getId());
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> opt = categoryRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("Category not found with id: " + id);
        }
        return opt.get();
    }
}
