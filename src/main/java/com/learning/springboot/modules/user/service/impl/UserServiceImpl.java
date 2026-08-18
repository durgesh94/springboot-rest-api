package com.learning.springboot.modules.user.service.impl;

import com.learning.springboot.exception.DuplicateResourceException;
import com.learning.springboot.exception.ResourceNotFoundException;
import com.learning.springboot.modules.user.dto.UserRequestDto;
import com.learning.springboot.modules.user.dto.UserResponseDto;
import com.learning.springboot.modules.user.entity.User;
import com.learning.springboot.modules.user.mapper.UserMapper;
import com.learning.springboot.modules.user.repository.UserRepository;
import com.learning.springboot.modules.user.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// @RequiredArgsConstructor : constructor injection
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // constructor injection
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequest) {
        // Step 1: Check existing email
        boolean isEmailExist = userRepository.existsByEmail(userRequest.getEmail());
        if (isEmailExist) {
            throw new DuplicateResourceException("Email already exists : " + userRequest.getEmail());
        }
        // Step 2: Convert dto to entity
        User user = UserMapper.toEntity(userRequest);
        // Step 3: Hash password
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        // Step 4: Save user
        User savedUser = userRepository.save(user);
        // Step 5: Convert entity to dto
        return UserMapper.toDto(savedUser);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return UserMapper.toDto(user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::toDto)
                .toList();
    }

    @Transactional
    // @Transactional annotation is used to manage transactions in the updateUser method. It ensures that the entire method is executed within a single transaction, which means that if any part of the method fails (e.g., if an exception is thrown), all changes made during the method execution will be rolled back, maintaining data integrity.
    @Override
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public UserResponseDto updateUser(Long id, UserRequestDto userRequest) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        // Check duplicate email only if the email is changed
        if (!existingUser.getEmail().equals(userRequest.getEmail())) {

            boolean isPresent = userRepository.existsByEmail(userRequest.getEmail());
            if (isPresent) {
                throw new DuplicateResourceException("Email already exists : " + userRequest.getEmail());
            }
        }

        UserMapper.updateEntity(existingUser, userRequest);

        User updatedUser = userRepository.save(existingUser);

        return UserMapper.toDto(updatedUser);
    }

    @Transactional
    // @Transactional annotation is used to manage transactions in the updateUser method. It ensures that the entire method is executed within a single transaction, which means that if any part of the method fails (e.g., if an exception is thrown), all changes made during the method execution will be rolled back, maintaining data integrity.
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        userRepository.delete(user);
    }
}
