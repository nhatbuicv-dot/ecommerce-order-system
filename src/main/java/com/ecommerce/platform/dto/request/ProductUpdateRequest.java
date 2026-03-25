package com.ecommerce.platform.dto.request;

import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "CATEGORY_ID_REQUIRED")
    String categoryId;

    List<ProductVariantCreateRequest> variants;
    List<String> images;

}