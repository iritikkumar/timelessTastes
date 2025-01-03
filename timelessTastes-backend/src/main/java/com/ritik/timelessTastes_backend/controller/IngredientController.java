package com.ritik.timelessTastes_backend.controller;

import com.ritik.timelessTastes_backend.model.IngredientCategory;
import com.ritik.timelessTastes_backend.model.IngredientItem;
import com.ritik.timelessTastes_backend.request.IngredientCategoryRequest;
import com.ritik.timelessTastes_backend.request.IngredientItemRequest;
import com.ritik.timelessTastes_backend.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/category")
    private ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody IngredientCategoryRequest req
            ) throws Exception {
        IngredientCategory item = ingredientsService.createIngredientCategory(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }


    @PostMapping()
    private ResponseEntity<IngredientItem> createIngredientItem(
            @RequestBody IngredientItemRequest req
            ) throws Exception {
        IngredientItem item = ingredientsService.createIngredientItem(req.getRestaurantId(), req.getName(), req.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }


    @PutMapping("/{id}/stock")
    private ResponseEntity<IngredientItem> updateIngredientStock(
            @PathVariable Long id
            ) throws Exception {
        IngredientItem item = ingredientsService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    private ResponseEntity<List<IngredientItem>> getRestaurantIngredient(
            @PathVariable Long id
            ) throws Exception {
        List<IngredientItem> items = ingredientsService.findRestaurantIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    private ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(
            @PathVariable Long id
            ) throws Exception {
        List<IngredientCategory> categories = ingredientsService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
