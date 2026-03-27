package com.ecommerce.platform.dto.response;

import com.ecommerce.platform.entity.OrderItem;
import com.ecommerce.platform.entity.ShippingAddress;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    String id;
    String userId;
    String status;
    List<OrderItemResponse> orderItems;
    Double total;
    LocalDateTime createdAt;
    ShippingAddress shippingAddress;
}
