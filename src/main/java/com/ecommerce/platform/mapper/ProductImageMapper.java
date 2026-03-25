package com.ecommerce.platform.mapper;

import com.ecommerce.platform.dto.request.ProductImageRequest;
import com.ecommerce.platform.dto.response.ProductImageResponse;
import com.ecommerce.platform.entity.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    ProductImage toProductImage(ProductImageRequest request);

    ProductImageResponse toProductImageResponse(ProductImage productImage);
}
