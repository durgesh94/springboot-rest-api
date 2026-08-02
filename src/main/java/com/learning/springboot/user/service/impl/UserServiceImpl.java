package com.learning.springboot.user.service.impl;

import com.learning.springboot.exception.DuplicateResourceException;
import com.learning.springboot.exception.ResourceNotFoundException;
import com.learning.springboot.user.dto.UserRequestDto;
import com.learning.springboot.user.dto.UserResponseDto;
import com.learning.springboot.user.entity.User;
import com.learning.springboot.user.mapper.UserMapper;
import com.learning.springboot.user.repository.UserRepository;
import com.learning.springboot.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// @RequiredArgsConstructor : constructor injection
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // constructor injection
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequest) {
        // Optional<User> existingUser = userRepository.findByEmail(userRequest.getEmail());
        boolean isEmailExist = userRepository.existsByEmail(userRequest.getEmail());
        if (isEmailExist) {
            throw new DuplicateResourceException("Email already exists : " + userRequest.getEmail());
        }
        User user = UserMapper.toEntity(userRequest);
        User savedUser = userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return UserMapper.toDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::toDto)
                .toList();
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        UserMapper.updateEntity(user, userRequest);

        User updatedUser = userRepository.save(user);

        return UserMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        userRepository.delete(user);
    }
}
