package com.learning.springboot.product.mapper;

import com.learning.springboot.product.dto.ProductRequestDto;
import com.learning.springboot.product.dto.ProductResponseDto;
import com.learning.springboot.product.entity.Product;

public class ProductMapper {

    private ProductMapper() {
        // prevent instantiation
    }

    public static Product toEntity(ProductRequestDto productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
    }

    public static ProductResponseDto toDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public static void updateEntity(
            Product product,
            ProductRequestDto productRequest
    ) {
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
    }
}
