package com.ecommerce.platform.mapper;

import com.ecommerce.platform.dto.response.OrderItemResponse;
import com.ecommerce.platform.entity.CartItem;
import com.ecommerce.platform.entity.OrderItem;
import com.ecommerce.platform.entity.Product;
import com.ecommerce.platform.entity.ProductVariant;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-25T09:14:32+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItem toOrderItem(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }

        OrderItem.OrderItemBuilder orderItem = OrderItem.builder();

        orderItem.productVariant( cartItem.getProductVariant() );
        orderItem.quantity( cartItem.getQuantity() );

        return orderItem.build();
    }

    @Override
    public OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemResponse.OrderItemResponseBuilder orderItemResponse = OrderItemResponse.builder();

        orderItemResponse.productName( orderItemProductVariantProductName( orderItem ) );
        orderItemResponse.price( orderItem.getPrice() );
        orderItemResponse.quantity( orderItem.getQuantity() );

        return orderItemResponse.build();
    }

    @Override
    public List<OrderItemResponse> toOrderItemResponse(List<OrderItem> orderItems) {
        if ( orderItems == null ) {
            return null;
        }

        List<OrderItemResponse> list = new ArrayList<OrderItemResponse>( orderItems.size() );
        for ( OrderItem orderItem : orderItems ) {
            list.add( toOrderItemResponse( orderItem ) );
        }

        return list;
    }

    private String orderItemProductVariantProductName(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }
        ProductVariant productVariant = orderItem.getProductVariant();
        if ( productVariant == null ) {
            return null;
        }
        Product product = productVariant.getProduct();
        if ( product == null ) {
            return null;
        }
        String name = product.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
