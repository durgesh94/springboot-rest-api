package com.learning.springboot.modules.order.service;

import com.learning.springboot.modules.order.dto.OrderRequestDto;
import com.learning.springboot.modules.order.dto.OrderResponseDto;
import com.learning.springboot.modules.order.dto.OrderStatusUpdateRequestDto;

import java.util.List;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto orderRequest);

    OrderResponseDto getOrderById(Long id);

    List<OrderResponseDto> getMyOrders();

    OrderResponseDto updateOrderStatus(Long id, OrderStatusUpdateRequestDto statusUpdateRequest);
}