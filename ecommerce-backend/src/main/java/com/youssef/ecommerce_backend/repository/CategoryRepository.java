package com.youssef.ecommerce_backend.repository;

import com.youssef.ecommerce_backend.model.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
}
