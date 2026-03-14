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
public class ProductCreateRequest {
    private String name;
    private String brand;
    private String description;
    private String categoryId;
    private List<String> images;
    private List<ProductVariantCreateRequest> variants;
}