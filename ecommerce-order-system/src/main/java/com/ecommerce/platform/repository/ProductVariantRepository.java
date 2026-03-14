package com.ecommerce.platform.repository;

import com.ecommerce.platform.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, String> {

}
