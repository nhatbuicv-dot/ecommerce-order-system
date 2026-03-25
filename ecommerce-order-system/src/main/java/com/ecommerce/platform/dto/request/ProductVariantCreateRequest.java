package com.ecommerce.platform.dto.request;

import com.ecommerce.platform.entity.Color;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariantCreateRequest {
    ColorRequest color;
    String size;
    double price;
    int quantity;
}