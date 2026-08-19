package com.learning.springboot.modules.order.repository;

import com.learning.springboot.modules.order.entity.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

  List<Order> findByUserId(Long userId);

  @Query(
      """
            SELECT DISTINCT o
            FROM Order o
            LEFT JOIN FETCH o.items i
            LEFT JOIN FETCH i.product
            WHERE o.id = :id
            """)
  Optional<Order> findByIdWithItems(@Param("id") Long id);

  @Query(
      """
            SELECT DISTINCT o
            FROM Order o
            LEFT JOIN FETCH o.items i
            LEFT JOIN FETCH i.product
            WHERE o.user.id = :userId
            """)
  List<Order> findByUserIdWithItems(@Param("userId") Long userId);
}
