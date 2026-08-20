package com.youssef.ecommerce_backend.repository;

import com.youssef.ecommerce_backend.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity ,Long> {
}
