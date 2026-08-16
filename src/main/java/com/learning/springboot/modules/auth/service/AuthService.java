package com.learning.springboot.modules.auth.service;

import com.learning.springboot.modules.auth.dto.LoginRequestDto;
import com.learning.springboot.modules.auth.dto.LoginResponseDto;

public interface AuthService {

    LoginResponseDto login(LoginRequestDto loginRequest);

}
