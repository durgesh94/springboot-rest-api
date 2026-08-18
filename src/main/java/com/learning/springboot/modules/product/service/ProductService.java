package com.learning.springboot.modules.product.service;

import com.learning.springboot.modules.product.dto.ProductRequestDto;
import com.learning.springboot.modules.product.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto productRequest);

    ProductResponseDto getProductById(Long id);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto updateProduct(Long id, ProductRequestDto productRequest);

    void deleteProduct(Long id);

}
