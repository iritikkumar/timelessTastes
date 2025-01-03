package com.ritik.timelessTastes_backend.repository;

import com.ritik.timelessTastes_backend.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
