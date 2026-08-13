package com.learning.springboot.product.service;

import com.learning.springboot.product.dto.ProductRequestDto;
import com.learning.springboot.product.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto productRequest);

    ProductResponseDto getProductById(Long id);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto updateProduct(Long id, ProductRequestDto productRequest);

    Void deleteProduct(Long id);

}
