package com.learning.springboot.modules.user.mapper;

import com.learning.springboot.modules.user.dto.UserRequestDto;
import com.learning.springboot.modules.user.dto.UserResponseDto;
import com.learning.springboot.modules.user.entity.Role;
import com.learning.springboot.modules.user.entity.User;

public class UserMapper {

  private UserMapper() {
    // Prevent instantiation
    // Since the class only contains static utility methods, there's no reason to create an object.
  }

  public static User toEntity(UserRequestDto userRequest) {
    return User.builder()
        .firstName(userRequest.getFirstName())
        .lastName(userRequest.getLastName())
        .email(userRequest.getEmail())
        .password(userRequest.getPassword())
        .role(Role.USER)
        .build();
  }

  public static UserResponseDto toDto(User user) {
    return UserResponseDto.builder()
        .id(user.getId())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .role(user.getRole())
        .createdAt(user.getCreatedAt())
        .updatedAt(user.getUpdatedAt())
        .build();
  }

  public static void updateEntity(User user, UserRequestDto dto) {
    user.setFirstName(dto.getFirstName());
    user.setLastName(dto.getLastName());
    user.setEmail(dto.getEmail());
    user.setPassword((dto.getPassword()));
  }
}
