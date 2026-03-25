package com.ecommerce.platform.mapper;

import com.ecommerce.platform.dto.request.CartItemRequest;
import com.ecommerce.platform.dto.response.CartItemResponse;
import com.ecommerce.platform.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItem toCartItem(CartItemRequest cartItemRequest);

    @Mapping(source = "productVariant.id", target = "productVariantId")
    CartItemResponse toCartItemResponse(CartItem cartItem);
}
