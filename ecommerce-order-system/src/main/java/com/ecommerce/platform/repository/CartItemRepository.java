package com.ecommerce.platform.repository;

import com.ecommerce.platform.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, String> {
    Optional<CartItem> findByCartIdAndProductVariantId(String cartId, String productVariantId);
}
