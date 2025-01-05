package com.ritik.timelessTastes_backend.repository;

import com.ritik.timelessTastes_backend.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
