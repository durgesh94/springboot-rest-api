package com.learning.springboot.modules.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {

  @NotEmpty(message = "Order must contain at least one item")
  @Valid
  private List<OrderItemRequestDto> items;
}
