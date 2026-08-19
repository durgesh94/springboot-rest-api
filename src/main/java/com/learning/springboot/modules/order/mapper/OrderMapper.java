package com.learning.springboot.modules.order.mapper;

import com.learning.springboot.modules.order.dto.OrderItemResponseDto;
import com.learning.springboot.modules.order.dto.OrderResponseDto;
import com.learning.springboot.modules.order.entity.Order;
import com.learning.springboot.modules.order.entity.OrderItem;
import java.math.BigDecimal;
import java.util.List;

public class OrderMapper {

  private OrderMapper() {}

  public static OrderResponseDto toDto(Order order) {
    List<OrderItemResponseDto> items =
        order.getItems().stream().map(OrderMapper::toItemDto).toList();
    BigDecimal totalAmount =
        items.stream()
            .map(OrderItemResponseDto::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    return OrderResponseDto.builder()
        .id(order.getId())
        .userId(order.getUser().getId())
        .status(order.getStatus())
        .totalAmount(totalAmount)
        .items(items)
        .build();
  }

  private static OrderItemResponseDto toItemDto(OrderItem item) {

    BigDecimal subtotal = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));

    return OrderItemResponseDto.builder()
        .productId(item.getProduct().getId())
        .productName(item.getProduct().getName())
        .quantity(item.getQuantity())
        .price(item.getPrice())
        .subtotal(subtotal)
        .build();
  }
}
