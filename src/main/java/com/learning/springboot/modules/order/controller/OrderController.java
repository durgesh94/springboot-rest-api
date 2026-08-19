package com.learning.springboot.modules.order.controller;

import com.learning.springboot.modules.order.dto.OrderRequestDto;
import com.learning.springboot.modules.order.dto.OrderResponseDto;
import com.learning.springboot.modules.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody OrderRequestDto orderRequest) {
        OrderResponseDto orderResponse = orderService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        OrderResponseDto orderResponse = orderService.getOrderById(id);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/my-orders")
    public ResponseEntity<List<OrderResponseDto>> getMyOrders() {
        List<OrderResponseDto> orders = orderService.getMyOrders();
        return ResponseEntity.ok(orders);
    }
}
