package com.ecommerce.platform.mapper;

import com.ecommerce.platform.dto.response.OrderItemResponse;
import com.ecommerce.platform.entity.CartItem;
import com.ecommerce.platform.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "price", ignore = true)
    OrderItem toOrderItem(CartItem cartItem);

    @Mapping(target = "productName", source = "productVariant.product.name")
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

    List<OrderItemResponse> toOrderItemResponse(List<OrderItem> orderItems);
}
