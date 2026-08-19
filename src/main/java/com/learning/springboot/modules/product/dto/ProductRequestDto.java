package com.learning.springboot.modules.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDto {

  @NotBlank(message = "Product name is required")
  private String name;

  @NotBlank(message = "Product description is required")
  private String description;

  @NotNull(message = "Product price is required")
  @DecimalMin(value = "0.01", message = "Product price must be greater than 0")
  private BigDecimal price;

  @NotNull(message = "Product quantity is required")
  @Min(value = 0, message = "Stock quantity cannot be negative")
  private Integer quantity;
}
