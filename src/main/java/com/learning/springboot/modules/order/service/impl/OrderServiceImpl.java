package com.learning.springboot.modules.order.service.impl;

import com.learning.springboot.exception.ResourceNotFoundException;
import com.learning.springboot.modules.order.dto.OrderItemRequestDto;
import com.learning.springboot.modules.order.dto.OrderRequestDto;
import com.learning.springboot.modules.order.dto.OrderResponseDto;
import com.learning.springboot.modules.order.entity.Order;
import com.learning.springboot.modules.order.entity.OrderItem;
import com.learning.springboot.modules.order.entity.OrderStatus;
import com.learning.springboot.modules.order.mapper.OrderMapper;
import com.learning.springboot.modules.order.repository.OrderItemRepository;
import com.learning.springboot.modules.order.repository.OrderRepository;
import com.learning.springboot.modules.order.service.OrderService;
import com.learning.springboot.modules.product.entity.Product;
import com.learning.springboot.modules.product.repository.ProductRepository;
import com.learning.springboot.modules.user.entity.User;
import com.learning.springboot.modules.user.repository.UserRepository;
import com.learning.springboot.security.util.SecurityUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto orderRequest) {
        // Step 1: Get currect auth user
        Long userId = SecurityUtils.getCurrentUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));
        // Step 2: Create order
        Order order = Order.builder()
                .user(user)
                .status(OrderStatus.PLACED)
                .build();
        // Step 3: Process each requested item
        for (OrderItemRequestDto itemRequest : orderRequest.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product", itemRequest.getProductId()));
            // Step 4: Get current product price as order price
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .price(product.getPrice())
                    .quantity(itemRequest.getQuantity())
                    .build();

            order.getItems().add(orderItem);
        }
        // Step 5: Save order + order items through the cascade
        Order saveOrder = orderRepository.save(order);
        // Step 6: Convert to response
        return OrderMapper.toDto(saveOrder);
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {

        Order order = orderRepository.findByIdWithItems(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order", id)
                );

        Long currentUserId = SecurityUtils.getCurrentUserId();

        boolean isAdmin = SecurityUtils.getCurrentUser()
                .getAuthorities()
                .stream()
                .anyMatch(authority ->
                        authority.getAuthority().equals("ROLE_ADMIN")
                );

        if (!isAdmin && !order.getUser().getId().equals(currentUserId)) {
            throw new AccessDeniedException(
                    "You are not allowed to access this order"
            );
        }

        return OrderMapper.toDto(order);
    }

    @Override
    public List<OrderResponseDto> getMyOrders() {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        List<Order> orders =
                orderRepository.findByUserIdWithItems(currentUserId);
        return orders.stream()
                .map(OrderMapper::toDto)
                .toList();
    }
}
