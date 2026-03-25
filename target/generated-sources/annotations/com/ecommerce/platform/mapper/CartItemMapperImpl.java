package com.ecommerce.platform.mapper;

import com.ecommerce.platform.dto.request.CartItemRequest;
import com.ecommerce.platform.dto.response.CartItemResponse;
import com.ecommerce.platform.entity.CartItem;
import com.ecommerce.platform.entity.ProductVariant;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-25T09:14:32+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class CartItemMapperImpl implements CartItemMapper {

    @Override
    public CartItem toCartItem(CartItemRequest cartItemRequest) {
        if ( cartItemRequest == null ) {
            return null;
        }

        CartItem.CartItemBuilder cartItem = CartItem.builder();

        cartItem.quantity( cartItemRequest.getQuantity() );

        return cartItem.build();
    }

    @Override
    public CartItemResponse toCartItemResponse(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }

        CartItemResponse.CartItemResponseBuilder cartItemResponse = CartItemResponse.builder();

        cartItemResponse.productVariantId( cartItemProductVariantId( cartItem ) );
        cartItemResponse.id( cartItem.getId() );
        cartItemResponse.quantity( cartItem.getQuantity() );

        return cartItemResponse.build();
    }

    private String cartItemProductVariantId(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }
        ProductVariant productVariant = cartItem.getProductVariant();
        if ( productVariant == null ) {
            return null;
        }
        String id = productVariant.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
