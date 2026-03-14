package com.ecommerce.platform.dto.response;

import com.ecommerce.platform.entity.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private String id;
    private String name;
    private String brand;
    private String description;

    private double rating;
    private int numReviews;

    private String categoryId;

    private List<ProductImageResponse> images;

    private List<ProductVariantResponse> variants;
}