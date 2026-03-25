package com.ecommerce.platform.dto.response;

import com.ecommerce.platform.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageResponse {
    String id;
    String imageUrl;
}
