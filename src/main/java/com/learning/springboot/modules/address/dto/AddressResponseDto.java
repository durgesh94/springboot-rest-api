package com.learning.springboot.modules.address.dto;

import java.time.LocalDateTime;

public class AddressResponseDto {

    private Long id;

    private Long userId;

    private String street;

    private String city;

    private String state;

    private String pincode;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
