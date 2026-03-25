package com.ecommerce.platform.repository;

import com.ecommerce.platform.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
