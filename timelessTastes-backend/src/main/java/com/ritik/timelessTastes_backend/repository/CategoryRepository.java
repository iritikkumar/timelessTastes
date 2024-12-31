package com.ritik.timelessTastes_backend.repository;

import com.ritik.timelessTastes_backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    public List<Category> findByRestaurantId(Long id);
}
