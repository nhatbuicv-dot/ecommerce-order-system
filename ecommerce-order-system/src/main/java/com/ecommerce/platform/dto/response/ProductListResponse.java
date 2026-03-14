package com.ecommerce.platform.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductListResponse {

    private String id;
    private String name;
    private String brand;

    private String thumbnail;     // image đầu tiên

    private Double minPrice;      // giá thấp nhất trong variants

    private Double rating;
    private Integer numReviews;
}