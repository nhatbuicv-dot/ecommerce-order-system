package com.ecommerce.platform.dto.response;

import com.ecommerce.platform.entity.Color;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariantResponse {
     String id;
     ColorResponse color;
     String size;
     double price;
     int quantity;
}