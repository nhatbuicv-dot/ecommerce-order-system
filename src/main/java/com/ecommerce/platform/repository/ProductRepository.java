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

    @EntityGraph(attributePaths = {"category"})
    Page<Product> findAll(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"category", "variants", "images"})
    Optional<Product> findById(String id);

    @Query(
            value = """
        SELECT new com.ecommerce.platform.dto.response.ProductListResponse(
            p.id,
            p.name,
            p.brand,
            MIN(i.imageUrl),
            p.minPrice,
            p.maxPrice
        )
        FROM Product p
        LEFT JOIN p.images i
        GROUP BY p.id, p.name, p.brand, p.minPrice, p.maxPrice
    """,
            countQuery = "SELECT COUNT(p) FROM Product p"
    )
    Page<ProductListResponse> getProductList(Pageable pageable);
}