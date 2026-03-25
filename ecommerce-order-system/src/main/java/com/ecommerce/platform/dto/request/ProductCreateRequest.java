package com.ecommerce.platform.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreateRequest {
     String name;
     String brand;
     String description;
     String categoryId;
     List<String> images;
     List<ProductVariantCreateRequest> variants;
}