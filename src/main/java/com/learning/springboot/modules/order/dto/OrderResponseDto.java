package com.learning.springboot.modules.order.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class OrderResponseDto {

    private Long id;
    private Long userId;
    private List<OrderItemResponseDto> items;
    private BigDecimal totalAmount;
}