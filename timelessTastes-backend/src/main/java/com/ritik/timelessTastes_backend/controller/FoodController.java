package com.ritik.timelessTastes_backend.controller;

import com.ritik.timelessTastes_backend.model.Food;
import com.ritik.timelessTastes_backend.model.Restaurant;
import com.ritik.timelessTastes_backend.model.User;
import com.ritik.timelessTastes_backend.service.FoodService;
import com.ritik.timelessTastes_backend.service.RestaurantService;
import com.ritik.timelessTastes_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String keyword,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Food> foods = foodService.searchFood(keyword);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @RequestParam boolean veg,
            @RequestParam boolean seasonal,
            @RequestParam boolean nonveg,
            @RequestParam(required = false) String food_category,
            @PathVariable Long restaurantId,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        List<Food> foods = foodService.getRestaurantsFood(restaurantId, veg, nonveg, seasonal, food_category);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }


}
