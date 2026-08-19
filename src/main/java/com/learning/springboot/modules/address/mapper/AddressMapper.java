package com.learning.springboot.modules.address.mapper;

import com.learning.springboot.modules.address.dto.AddressRequestDto;
import com.learning.springboot.modules.address.dto.AddressResponseDto;
import com.learning.springboot.modules.address.entity.Address;
import com.learning.springboot.modules.address.entity.AddressType;
import com.learning.springboot.modules.user.entity.User;

public class AddressMapper {

  private AddressMapper() {
    // prevent instantiation
  }

  public static Address toEntity(AddressRequestDto addressRequest, User user) {
    return Address.builder()
        .user(user)
        .type(AddressType.valueOf(addressRequest.getType()))
        .street(addressRequest.getStreet())
        .city(addressRequest.getCity())
        .state(addressRequest.getState())
        .pincode(addressRequest.getPincode())
        .build();
  }

  public static AddressResponseDto toDto(Address address) {
    return AddressResponseDto.builder()
        .id(address.getId())
        .userId(address.getUser().getId())
        .street(address.getStreet())
        .city(address.getCity())
        .state(address.getState())
        .pincode(address.getPincode())
        .createdAt(address.getCreatedAt())
        .updatedAt(address.getUpdatedAt())
        .build();
  }

  public static void updateEntity(Address address, AddressRequestDto addressRequest, User user) {
    address.setUser(user);
    address.setType(AddressType.valueOf(addressRequest.getType()));
    address.setStreet(addressRequest.getStreet());
    address.setCity(addressRequest.getCity());
    address.setState(addressRequest.getState());
    address.setPincode(address.getPincode());
  }
}
