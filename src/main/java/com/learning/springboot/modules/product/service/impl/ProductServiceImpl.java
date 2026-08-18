package com.learning.springboot.modules.product.service.impl;

import com.learning.springboot.exception.ResourceNotFoundException;
import com.learning.springboot.modules.product.dto.ProductRequestDto;
import com.learning.springboot.modules.product.dto.ProductResponseDto;
import com.learning.springboot.modules.product.entity.Product;
import com.learning.springboot.modules.product.mapper.ProductMapper;
import com.learning.springboot.modules.product.repository.ProductRepository;
import com.learning.springboot.modules.product.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequest) {
        Product product = ProductMapper.toEntity(productRequest);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDto(savedProduct);
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return ProductMapper.toDto(product);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequest) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        ProductMapper.updateEntity(existingProduct, productRequest);
        Product updatedProduct = productRepository.save(existingProduct);
        return ProductMapper.toDto(updatedProduct);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        productRepository.delete(product);
        return null;
    }

}
