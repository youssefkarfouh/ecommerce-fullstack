package com.youssef.ecommerce_backend.repository;

import com.youssef.ecommerce_backend.model.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {}