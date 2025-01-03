package com.ritik.timelessTastes_backend.request;

import com.ritik.timelessTastes_backend.model.Category;
import com.ritik.timelessTastes_backend.model.IngredientItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;

    private Category category;
    private List<String> images;

    private Long restaurantId;
    private boolean veg;
    private boolean seasonal;
    private List<IngredientItem> ingredients;
}
