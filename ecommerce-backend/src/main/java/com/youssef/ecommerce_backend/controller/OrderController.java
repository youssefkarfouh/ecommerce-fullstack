package com.youssef.ecommerce_backend.controller;

import com.youssef.ecommerce_backend.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}/product-names")
    public List<String> getOrderProductNames(@PathVariable Long id) {
        return orderService.getOrderProductNames(id);
    }
}
