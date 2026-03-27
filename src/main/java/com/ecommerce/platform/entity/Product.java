package com.ecommerce.platform.entity;

import com.ecommerce.platform.enums.OrderStatus;
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

    Double minPrice;
    Double maxPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    public void addVariant(ProductVariant variant){
        variants.add(variant);
        variant.setProduct(this);
    }

    public void addImage(ProductImage image){
        images.add(image);
        image.setProduct(this);
    }

    public void recalculatePrice(){
        if(variants == null || variants.isEmpty()){
            this.maxPrice = null ;
            this.minPrice = null ;
        }

        this.minPrice = variants.stream().mapToDouble(value -> value.getPrice()).min().orElse(0.0);

        this.maxPrice = variants.stream().mapToDouble(value -> value.getPrice()).max().orElse(0.0);
    }
}