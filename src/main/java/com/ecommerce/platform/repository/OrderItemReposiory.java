package com.ecommerce.platform.repository;

import com.ecommerce.platform.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemReposiory extends JpaRepository<OrderItem, String> {
}
