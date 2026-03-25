package com.ecommerce.platform.service;

import com.ecommerce.platform.configuration.SecurityUtils;
import com.ecommerce.platform.dto.response.CartItemResponse;
import com.ecommerce.platform.dto.response.CartResponse;
import com.ecommerce.platform.entity.Cart;
import com.ecommerce.platform.entity.User;
import com.ecommerce.platform.mapper.CartItemMapper;
import com.ecommerce.platform.repository.CartRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CartService {
    CartRepository cartRepository;
    SecurityUtils securityUtils;
    CartItemMapper cartItemMapper;

    public CartResponse getCart() {
        User user = securityUtils.getCurrentUser();

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> cartRepository.save(Cart.builder().user(user).build()));

        List<CartItemResponse> itemResponses = cart.getCartItems().stream()
                .map(cartItemMapper::toCartItemResponse)
                .toList();

        return CartResponse.builder()
                .id(cart.getId())
                .items(itemResponses)
                .totalPrice(calculateTotalPrice(cart))
                .totalItems(cart.getCartItems().size())
                .build();
    }

    private Double calculateTotalPrice(Cart cart) {
        return cart.getCartItems().stream()
                .mapToDouble(item -> item.getProductVariant().getPrice() * item.getQuantity())
                .sum();
    }
}