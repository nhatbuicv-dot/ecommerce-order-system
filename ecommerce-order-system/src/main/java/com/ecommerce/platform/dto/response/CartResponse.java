package com.ecommerce.platform.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponse {
    String id ;
    List<CartItemResponse> items;
    Double totalPrice;
    Integer totalItems;
}
