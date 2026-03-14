package com.ecommerce.platform.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String name;
    String brand;

    @Column(columnDefinition = "TEXT")
    String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    Category category;

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL , orphanRemoval = true)
    List<ProductVariant> variants = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL , orphanRemoval = true)
    List<ProductImage> images = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL , orphanRemoval = true)
    List<Review> reviews = new ArrayList<>();

    @Formula("(SELECT MIN(v.price) FROM product_variant v WHERE v.product_id = id)")
    Double minPrice;

    @Formula("(SELECT MAX(v.price) FROM product_variant v WHERE v.product_id = id)")
    Double maxPrice;

    @Formula("(SELECT AVG(r.rating) FROM review r WHERE r.product_id = id)")
    Double rating;

    @Formula("(SELECT COUNT(r.id) FROM review r WHERE r.product_id = id)")
    Integer numReviews;

    public void addVariant(ProductVariant variant){
        variants.add(variant);
        variant.setProduct(this);
    }

    public void addImage(ProductImage image){
        images.add(image);
        image.setProduct(this);
    }
}
