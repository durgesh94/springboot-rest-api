package com.learning.springboot.modules.order.dto;

import com.learning.springboot.modules.order.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatusUpdateRequestDto {

  @NotNull(message = "Order status is required")
  private OrderStatus status;
}
