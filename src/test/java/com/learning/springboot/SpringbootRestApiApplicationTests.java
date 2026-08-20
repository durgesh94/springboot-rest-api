package com.learning.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class SpringbootRestApiApplicationTests {

  @Test
  void contextLoads() {
    // Intentionally left empty. This test ensures that the Spring application context loads
    // successfully without any issues.
  }
}
