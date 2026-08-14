package com.learning.springboot.modules.address.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressRequestDto {

    @NotNull(message = "User Id is required")
    private Long userId;

    @NotNull(message = "Address type is required")
    private String type;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Pincode is required")
    @Pattern(
            regexp = "^[0-9]{6}$",
            message = "Pincode must be valid 6-digit number"
    )
    private String pincode;
}
