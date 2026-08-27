package com.youssef.ecommerce_backend.repository;

import com.youssef.ecommerce_backend.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity ,Long> {


    @Query(value = "SELECT p FROM ProductEntity p JOIN FETCH p.category")
    List<ProductEntity> findAllWithCategory();
}
