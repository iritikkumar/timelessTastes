package com.ritik.timelessTastes_backend.request;

import lombok.Data;

@Data
public class IngredientItemRequest {
    private Long restaurantId;
    private String name;
    private Long categoryId;
}
