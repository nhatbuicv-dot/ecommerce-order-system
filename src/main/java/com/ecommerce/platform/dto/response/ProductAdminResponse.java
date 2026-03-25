package com.ecommerce.platform.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductAdminResponse {

     String id;
     String name;
     String brand;
     String description;
     String categoryName;

     int totalStock;
     LocalDateTime createdAt;
     LocalDateTime updatedAt;
}