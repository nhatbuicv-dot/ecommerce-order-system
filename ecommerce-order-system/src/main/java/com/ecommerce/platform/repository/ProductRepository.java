package com.ecommerce.platform.repository;

import com.ecommerce.platform.dto.response.ProductListResponse;
import com.ecommerce.platform.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findByName(String productName);

    @EntityGraph(attributePaths = {"category", "images", "variants", "reviews"})
    Page<Product> findAll(Pageable pageable);

    @Query("""
            SELECT new com.ecommerce.platform.dto.response.ProductListResponse(
                p.id,
                p.name,
                p.brand,
                (SELECT i.imageUrl 
                    FROM ProductImage i 
                    WHERE i.id = (
                        SELECT MIN(i2.id) 
                        FROM ProductImage i2 
                        WHERE i2.product.id = p.id
                    )
                ),
                p.minPrice,
                p.rating,
                p.numReviews
            )
            FROM Product p
            """)
    Page<ProductListResponse> getProductList(Pageable pageable);
}
