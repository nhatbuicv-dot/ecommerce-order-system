package com.ecommerce.platform.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductAdminResponse {

    private String id;
    private String name;
    private String brand;
    private String description;

    private double rating;
    private int numReviews;

    private String categoryName;

    private int totalStock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}