package com.learning.springboot.modules.user.dto;

import com.learning.springboot.modules.user.entity.Role;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

  private Long id;

  private String firstName;

  private String lastName;

  private String email;

  private Role role;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
