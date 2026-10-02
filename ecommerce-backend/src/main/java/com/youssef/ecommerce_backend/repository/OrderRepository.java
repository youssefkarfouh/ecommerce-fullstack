package com.youssef.ecommerce_backend.repository;

import com.youssef.ecommerce_backend.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {}
