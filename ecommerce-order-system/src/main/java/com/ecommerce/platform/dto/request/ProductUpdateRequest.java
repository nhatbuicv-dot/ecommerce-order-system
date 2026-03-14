package com.ecommerce.platform.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest {
    String name;
    String brand;
    String description;
    String categoryId;

    List<ProductVariantCreateRequest> variants;
    List<String> images;

}