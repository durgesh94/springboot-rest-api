package com.learning.springboot.modules.order.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItemResponseDto {

  private Long productId;
  private String productName;
  private Integer quantity;
  private BigDecimal price;
  private BigDecimal subtotal;
}
