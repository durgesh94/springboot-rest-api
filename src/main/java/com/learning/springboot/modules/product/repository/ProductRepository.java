package com.learning.springboot.modules.product.repository;

import com.learning.springboot.modules.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
