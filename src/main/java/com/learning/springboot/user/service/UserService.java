package com.learning.springboot.user.service;

import com.learning.springboot.user.dto.UserRequestDto;
import com.learning.springboot.user.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequest);

    UserResponseDto getUserById(Long id);

    List<UserResponseDto> getAllUsers();

    UserResponseDto updateUser(Long id, UserRequestDto userRequest);

    void deleteUserById(Long id);

}
