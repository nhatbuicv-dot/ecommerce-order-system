package com.ecommerce.platform.dto.response;

import com.ecommerce.platform.entity.ProductImage;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {

    String id;
    String name;
    String brand;
    String description;
    String categoryName;
    Double minPrice;
    Double maxPrice;

    List<ProductImageResponse> images;

    List<ProductVariantResponse> variants;
}