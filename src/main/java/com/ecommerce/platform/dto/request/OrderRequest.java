package com.ecommerce.platform.dto.request;

import com.ecommerce.platform.entity.OrderItem;
import com.ecommerce.platform.entity.ShippingAddress;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    ShippingAddress shippingAddress;
}
