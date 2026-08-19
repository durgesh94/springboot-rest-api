package com.learning.springboot.listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupListener {

  @EventListener(ApplicationReadyEvent.class)
  public void onApplicationReady() {

    System.out.println(
        """

                ==============================================
                 🚀 Spring Boot REST API Started Successfully!
                 🌐 URL : http://localhost:8080
                ==============================================
                """);
  }
}
