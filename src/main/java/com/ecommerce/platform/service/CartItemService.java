package com.ecommerce.platform.service;

import com.ecommerce.platform.configuration.SecurityUtils;
import com.ecommerce.platform.dto.request.CartItemRequest;
import com.ecommerce.platform.dto.response.CartItemResponse;
import com.ecommerce.platform.entity.Cart;
import com.ecommerce.platform.entity.CartItem;
import com.ecommerce.platform.entity.User;
import com.ecommerce.platform.exception.AppException;
import com.ecommerce.platform.exception.ErrorCode;
import com.ecommerce.platform.mapper.CartItemMapper;
import com.ecommerce.platform.repository.CartItemRepository;
import com.ecommerce.platform.repository.CartRepository;
import com.ecommerce.platform.repository.ProductVariantRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CartItemService {

    ProductVariantRepository productVariantRepository;
    CartItemMapper cartItemMapper;
    CartItemRepository cartItemRepository;
    CartRepository cartRepository;
    SecurityUtils securityUtils;


    // tạo và update
    public CartItemResponse upsertCartItem(CartItemRequest cartItemRequest) {

        var productVariant = productVariantRepository.findById(cartItemRequest.getProductVariantId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_VARIANT_NOT_FOUND));

        User user = securityUtils.getCurrentUser();

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        CartItem existing = cartItemRepository
                .findByCartIdAndProductVariantId(cart.getId(), cartItemRequest.getProductVariantId())
                .orElse(null);

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + cartItemRequest.getQuantity());
            return cartItemMapper.toCartItemResponse(cartItemRepository.save(existing));
        }

        CartItem cartItem = cartItemMapper.toCartItem(cartItemRequest);
        cartItem.setProductVariant(productVariant);
        cartItem.setCart(cart);

        return cartItemMapper.toCartItemResponse(cartItemRepository.save(cartItem));
    }

}
