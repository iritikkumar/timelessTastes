package com.ritik.timelessTastes_backend.controller;

import com.ritik.timelessTastes_backend.dto.RestaurantDto;
import com.ritik.timelessTastes_backend.model.Restaurant;
import com.ritik.timelessTastes_backend.model.User;
import com.ritik.timelessTastes_backend.service.RestaurantService;
import com.ritik.timelessTastes_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(
            @RequestHeader String jwt,
            @RequestParam String keyword
        ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurants = restaurantService.searchRestaurant(keyword);

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Restaurant>> getAllRestaurant(
            @RequestHeader String jwt
        ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurants = restaurantService.getAllRestaurant();

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(
            @RequestHeader String jwt,
            @PathVariable Long id
        ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favourite")
    public ResponseEntity<RestaurantDto> addToFavourites(
            @RequestHeader String jwt,
            @PathVariable Long id
        ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        RestaurantDto dto = restaurantService.addToFavourites(id, user);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
