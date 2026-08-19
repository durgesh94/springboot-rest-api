package com.learning.springboot.modules.order.repository;

import com.learning.springboot.modules.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {}
