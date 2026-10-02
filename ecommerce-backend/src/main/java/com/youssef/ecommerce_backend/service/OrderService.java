package com.youssef.ecommerce_backend.service;

import com.youssef.ecommerce_backend.exception.ResourceNotFoundException;
import com.youssef.ecommerce_backend.model.dto.order.OrderRequest;
import com.youssef.ecommerce_backend.model.entity.OrderEntity;
import com.youssef.ecommerce_backend.repository.OrderRepository;
import com.youssef.ecommerce_backend.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public List<String> getOrderProductNames(Long id) {

        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id " + id + " not found"));

        return order.getOrderItems().stream()
                .map(item -> item.getProduct().getName())
                .toList();
    }

    public void createOrder (OrderRequest order) {



    }
}
