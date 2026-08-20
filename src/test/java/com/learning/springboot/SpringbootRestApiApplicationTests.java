package com.learning.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class SpringbootRestApiApplicationTests {

  @Test
  void contextLoads() {
    // This test will pass if the application context loads successfully, indicating that the Spring Boot application is configured correctly and all necessary beans are available.
  }
}
