package com.learning.springboot.modules.auth.controller;

import com.learning.springboot.modules.auth.dto.LoginRequestDto;
import com.learning.springboot.modules.auth.dto.LoginResponseDto;
import com.learning.springboot.modules.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequest) {

        LoginResponseDto loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);

    }
}
