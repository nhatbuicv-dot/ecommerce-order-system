package com.ecommerce.platform.mapper;

import com.ecommerce.platform.dto.request.ProductVariantCreateRequest;
import com.ecommerce.platform.dto.response.ProductVariantResponse;
import com.ecommerce.platform.entity.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {
    @Mapping(target = "color", source = "color")
    ProductVariantResponse toProductVariantResponse(ProductVariant productVariant);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    ProductVariant toProductVariant(ProductVariantCreateRequest variantAddRequest);

    void updateProductVariant(@MappingTarget ProductVariant productVariant, ProductVariantCreateRequest request);
}
