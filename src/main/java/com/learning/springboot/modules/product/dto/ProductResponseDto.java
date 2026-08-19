package com.learning.springboot.modules.product.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {

  private Long id;

  private String name;

  private String description;

  private BigDecimal price;

  private Integer quantity;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
