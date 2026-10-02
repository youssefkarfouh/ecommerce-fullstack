package com.youssef.ecommerce_backend.service;

import com.youssef.ecommerce_backend.exception.ResourceNotFoundException;
import com.youssef.ecommerce_backend.model.dto.category.CategoryResponse;
import com.youssef.ecommerce_backend.model.dto.product.ProductRequest;
import com.youssef.ecommerce_backend.model.dto.product.ProductResponse;
import com.youssef.ecommerce_backend.model.entity.CategoryEntity;
import com.youssef.ecommerce_backend.model.entity.ProductEntity;
import com.youssef.ecommerce_backend.repository.CategoryRepository;
import com.youssef.ecommerce_backend.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductResponse createProduct(ProductRequest request) {

        CategoryEntity category =  categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        ProductEntity pEntity = new ProductEntity();

        pEntity.setName(request.getName());
        pEntity.setDescription(request.getDescription());
        pEntity.setPrice(request.getPrice());
        pEntity.setStockQuantity(request.getStockQuantity());
        pEntity.setCategory(category);


        ProductEntity savedProduct = productRepository.save(pEntity);

        return mapToResponse(savedProduct);

    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAllWithCategory()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    public ProductResponse getProductById(Long id) {

        ProductEntity product = productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product with id " + id + " not found"
                ));


        System.out.println(product);

        return mapToResponse(product);
    }

    public void deleteProduct(Long id) {

        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
        productRepository.delete(productEntity);
    }

    public ProductResponse updateProduct(Long id , ProductRequest request) {

        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        productEntity.setName(request.getName());
        productEntity.setDescription(request.getDescription());
        productEntity.setPrice(request.getPrice());
        productEntity.setStockQuantity(request.getStockQuantity());
        ProductEntity updatedProduct =  productRepository.save(productEntity);

        return mapToResponse(updatedProduct);

    }

    private ProductResponse mapToResponse(ProductEntity product) {

        ProductResponse response = new ProductResponse();
        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setId(product.getCategory().getId());
        categoryResponse.setName(product.getCategory().getName());

        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStockQuantity(product.getStockQuantity());
        response.setCategory(categoryResponse);

        return response;
    }

}
