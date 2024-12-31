package com.ritik.timelessTastes_backend.repository;

import com.ritik.timelessTastes_backend.model.IngredientItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientItemRepository extends JpaRepository<IngredientItem, Long> {

    List<IngredientItem> findByRestaurantId(Long id);
}
