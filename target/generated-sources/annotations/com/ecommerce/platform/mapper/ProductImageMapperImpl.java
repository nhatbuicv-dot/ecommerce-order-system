package com.ecommerce.platform.mapper;

import com.ecommerce.platform.dto.request.ProductImageRequest;
import com.ecommerce.platform.dto.response.ProductImageResponse;
import com.ecommerce.platform.entity.ProductImage;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-25T09:14:32+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class ProductImageMapperImpl implements ProductImageMapper {

    @Override
    public ProductImage toProductImage(ProductImageRequest request) {
        if ( request == null ) {
            return null;
        }

        ProductImage.ProductImageBuilder productImage = ProductImage.builder();

        productImage.imageUrl( request.getImageUrl() );

        return productImage.build();
    }

    @Override
    public ProductImageResponse toProductImageResponse(ProductImage productImage) {
        if ( productImage == null ) {
            return null;
        }

        ProductImageResponse.ProductImageResponseBuilder productImageResponse = ProductImageResponse.builder();

        productImageResponse.id( productImage.getId() );
        productImageResponse.imageUrl( productImage.getImageUrl() );

        return productImageResponse.build();
    }
}
