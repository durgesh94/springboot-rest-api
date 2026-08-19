package com.learning.springboot.modules.order.dto;
import com.learning.springboot.modules.order.entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class OrderResponseDto {

    private Long id;
    private Long userId;
    private OrderStatus status;
    private List<OrderItemResponseDto> items;
    private BigDecimal totalAmount;
}