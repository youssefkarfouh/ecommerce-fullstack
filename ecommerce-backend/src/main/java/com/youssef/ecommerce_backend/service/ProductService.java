package com.youssef.ecommerce_backend.service;

import com.youssef.ecommerce_backend.exception.ResourceNotFoundException;
import com.youssef.ecommerce_backend.model.dto.product.ProductRequest;
import com.youssef.ecommerce_backend.model.dto.product.ProductResponse;
import com.youssef.ecommerce_backend.model.entity.ProductEntity;
import com.youssef.ecommerce_backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(ProductRequest request) {

        ProductEntity pEntity = new ProductEntity();

        pEntity.setName(request.getName());
        pEntity.setDescription(request.getDescription());
        pEntity.setPrice(request.getPrice());
        pEntity.setStockQuantity(request.getStockQuantity());

        ProductEntity savedProduct = productRepository.save(pEntity);

        return mapToResponse(savedProduct);

    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(item -> mapToResponse(item))
                .toList();
    }

    public ProductResponse getProductById(Long id) {

        ProductEntity product = productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product with id " + id + " not found"
                ));

        return mapToResponse(product);
    }

    public void deleteProduct(Long id) {

        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
        productRepository.delete(productEntity);
    }
    public ProductResponse updateProduct(Long id){
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));


    }

    private ProductResponse mapToResponse(ProductEntity product) {

        ProductResponse response = new ProductResponse();

        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStockQuantity(product.getStockQuantity());

        return response;
    }

}
