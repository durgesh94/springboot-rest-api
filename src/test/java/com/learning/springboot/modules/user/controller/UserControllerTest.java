package com.learning.springboot.modules.user.controller;

import com.learning.springboot.modules.user.dto.UserRequestDto;
import com.learning.springboot.modules.user.dto.UserResponseDto;
import com.learning.springboot.modules.user.service.UserService;
import com.learning.springboot.security.handler.JwtAuthenticationEntryPoint;
import com.learning.springboot.security.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockitoBean
    private UserDetailsService userDetailsService;


    @Test
    void createUser() throws Exception {
        // Arrange
        UserResponseDto response = new UserResponseDto();
        response.setId(1L);
        response.setFirstName("Durgesh");
        response.setLastName("Tambe");
        response.setEmail("durgesh@gmail.com");

        when(userService.createUser(any(UserRequestDto.class)))
                .thenReturn(response);

        // Act & Assert
        mockMvc.perform(
                        org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                                .post("/api/v1/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "firstName": "Durgesh",
                                            "lastName": "Tambe",
                                            "email": "durgesh@gmail.com",
                                            "password": "password123"
                                        }
                                        """)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Durgesh"))
                .andExpect(jsonPath("$.lastName").value("Tambe"))
                .andExpect(jsonPath("$.email").value("durgesh@gmail.com"));

        // Verify service was called
        verify(userService).createUser(any(UserRequestDto.class));
    }
}
