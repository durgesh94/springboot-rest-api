package com.learning.springboot.modules.user.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.learning.springboot.exception.DuplicateResourceException;
import com.learning.springboot.modules.user.dto.UserRequestDto;
import com.learning.springboot.modules.user.dto.UserResponseDto;
import com.learning.springboot.modules.user.entity.User;
import com.learning.springboot.modules.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

  @Mock private UserRepository userRepository;

  @Mock private PasswordEncoder passwordEncoder;

  @InjectMocks private UserServiceImpl userService;

  @Test
  void testCreateUser() {
    // Implement your test logic here
    // Arrange: Prepare the input data and mock behaviors
    UserRequestDto userRequest = new UserRequestDto();
    userRequest.setFirstName("Durgesh");
    userRequest.setLastName("Tambe");
    userRequest.setEmail("durgesh@gmail.com");
    userRequest.setPassword("password123");

    User user = new User();
    user.setId(1L);
    user.setFirstName(userRequest.getFirstName());
    user.setLastName(userRequest.getLastName());
    user.setEmail(userRequest.getEmail());
    user.setPassword("hashedPassword");

    when(userRepository.existsByEmail("durgesh@gmail.com")).thenReturn(false);
    when(passwordEncoder.encode("password123")).thenReturn("hashedPassword");
    when(userRepository.save(any(User.class))).thenReturn(user);

    // Act: Call the method under test
    UserResponseDto response = userService.createUser(userRequest);

    // Assert: Verify the results
    assertNotNull(response);
    assertEquals(1L, response.getId());
    assertEquals("Durgesh", response.getFirstName());
    assertEquals("Tambe", response.getLastName());
    assertEquals("durgesh@gmail.com", response.getEmail());

    // Verify that the mocks were called as expected
    verify(userRepository).existsByEmail("durgesh@gmail.com");
    verify(passwordEncoder).encode("password123");
    verify(userRepository).save(any(User.class));
  }

  @Test
  void createUser_shouldThrowExceptionWhenEmailAlreadyExists() {
    // Arrange
    UserRequestDto userRequest = new UserRequestDto();
    userRequest.setEmail("durgesh@gmail.com");
    userRequest.setFirstName("Durgesh");
    userRequest.setLastName("Tambe");
    userRequest.setPassword("password123");

    when(userRepository.existsByEmail("durgesh@gmail.com")).thenReturn(true);

    // Act & Assert
    DuplicateResourceException exception =
        assertThrows(DuplicateResourceException.class, () -> userService.createUser(userRequest));
    assertEquals("Email already exists : durgesh@gmail.com", exception.getMessage());

    // Verify that the repository method was called
    verify(userRepository).existsByEmail("durgesh@gmail.com");
  }
}
