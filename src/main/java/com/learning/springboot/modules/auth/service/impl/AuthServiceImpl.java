package com.learning.springboot.modules.auth.service.impl;

import com.learning.springboot.modules.auth.dto.LoginRequestDto;
import com.learning.springboot.modules.auth.dto.LoginResponseDto;
import com.learning.springboot.modules.auth.service.AuthService;
import com.learning.springboot.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  // private final UserRepository userRepository;
  // private final PasswordEncoder passwordEncoder;
  // Above both replace with authenticationManager
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  @Override
  public LoginResponseDto login(LoginRequestDto loginRequest) {
    //        // Step 1: Verify user
    //        User user = userRepository.findByEmail(loginRequest.getEmail())
    //                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or
    // password"));
    //        // Step 2: Check password match
    //        boolean passwordMatched = passwordEncoder.matches(loginRequest.getPassword(),
    // user.getPassword());
    //        if (!passwordMatched) {
    //            throw new InvalidCredentialsException("Invalid email or password");
    //        }
    // Step 1&2: replace with authenticationManager
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequest.getEmail(), loginRequest.getPassword()));
    // New Step: Generate JWT token
    String token = jwtService.generateToken(loginRequest.getEmail());
    // Step 3: Login success
    return LoginResponseDto.builder()
        .message("Login successful")
        .token(token)
        .tokenType("Bearer")
        .build();
  }
}
