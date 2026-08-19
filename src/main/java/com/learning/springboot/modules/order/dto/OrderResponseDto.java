package com.learning.springboot.modules.order.dto;

import com.learning.springboot.modules.order.entity.OrderStatus;
import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderResponseDto {

  private Long id;
  private Long userId;
  private OrderStatus status;
  private List<OrderItemResponseDto> items;
  private BigDecimal totalAmount;
}
