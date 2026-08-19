package com.learning.springboot.modules.user.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.learning.springboot.modules.user.dto.UserRequestDto;
import com.learning.springboot.modules.user.dto.UserResponseDto;
import com.learning.springboot.modules.user.entity.Role;
import com.learning.springboot.modules.user.entity.User;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class UserMapperTest {

  @Test
  void toEntity_shouldMapRequestDtoToUser() {
    UserRequestDto request =
        UserRequestDto.builder()
            .firstName("Durgesh")
            .lastName("Tambe")
            .email("durgesh@gmail.com")
            .password("password123")
            .build();

    User user = UserMapper.toEntity(request);

    assertThat(user.getFirstName()).isEqualTo("Durgesh");
    assertThat(user.getLastName()).isEqualTo("Tambe");
    assertThat(user.getEmail()).isEqualTo("durgesh@gmail.com");
    assertThat(user.getPassword()).isEqualTo("password123");
    assertThat(user.getRole()).isEqualTo(Role.USER);
  }

  @Test
  void toDto_shouldMapUserToResponseDto() {
    LocalDateTime now = LocalDateTime.now();
    User user =
        User.builder()
            .id(1L)
            .firstName("Durgesh")
            .lastName("Tambe")
            .email("durgesh@gmail.com")
            .role(Role.ADMIN)
            .build();
    user.setCreatedAt(now);
    user.setUpdatedAt(now);

    UserResponseDto dto = UserMapper.toDto(user);

    assertThat(dto.getId()).isEqualTo(1L);
    assertThat(dto.getFirstName()).isEqualTo("Durgesh");
    assertThat(dto.getLastName()).isEqualTo("Tambe");
    assertThat(dto.getEmail()).isEqualTo("durgesh@gmail.com");
    assertThat(dto.getRole()).isEqualTo(Role.ADMIN);
    assertThat(dto.getCreatedAt()).isEqualTo(now);
    assertThat(dto.getUpdatedAt()).isEqualTo(now);
  }

  @Test
  void updateEntity_shouldUpdateUserFields() {
    User user =
        User.builder()
            .id(1L)
            .firstName("Old")
            .lastName("Name")
            .email("old@gmail.com")
            .password("oldpass")
            .role(Role.USER)
            .build();

    UserRequestDto request =
        UserRequestDto.builder()
            .firstName("New")
            .lastName("Name2")
            .email("new@gmail.com")
            .password("newpass")
            .build();

    UserMapper.updateEntity(user, request);

    assertThat(user.getFirstName()).isEqualTo("New");
    assertThat(user.getLastName()).isEqualTo("Name2");
    assertThat(user.getEmail()).isEqualTo("new@gmail.com");
    assertThat(user.getPassword()).isEqualTo("newpass");
    assertThat(user.getRole()).isEqualTo(Role.USER);
  }
}
