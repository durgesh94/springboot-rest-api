package com.learning.springboot.modules.order.repository;

import com.learning.springboot.modules.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}