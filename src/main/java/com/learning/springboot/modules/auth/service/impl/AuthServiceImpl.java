package com.learning.springboot.modules.auth.service.impl;

import com.learning.springboot.exception.InvalidCredentialsException;
import com.learning.springboot.modules.auth.dto.LoginRequestDto;
import com.learning.springboot.modules.auth.dto.LoginResponseDto;
import com.learning.springboot.modules.auth.service.AuthService;
import com.learning.springboot.modules.user.entity.User;
import com.learning.springboot.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequest) {
        // Step 1: Verify user
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));
        // Step 2: Check password match
        boolean passwordMatched = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if (!passwordMatched) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
        // Step 3: Login success
        return LoginResponseDto.builder()
                .message("Login successful")
                .build();
    }

}
