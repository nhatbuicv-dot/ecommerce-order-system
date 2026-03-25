package com.ecommerce.platform.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductListResponse {
     String id;
     String name;
     String brand;
     String thumbnail;
     Double minPrice;
     Double maxPrice;
}