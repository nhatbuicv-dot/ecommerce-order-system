package com.ecommerce.platform.mapper;

import com.ecommerce.platform.dto.request.ProductCreateRequest;
import com.ecommerce.platform.dto.request.ProductUpdateRequest;
import com.ecommerce.platform.dto.response.ProductResponse;
import com.ecommerce.platform.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        uses = {ProductVariantMapper.class}
)
public interface ProductMapper {

    @Mapping(target = "variants", source = "variants")
    ProductResponse toProductResponse(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "variants", ignore = true)
    @Mapping(target = "images", ignore = true)
    Product toProduct(ProductCreateRequest request);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "variants", ignore = true)
    @Mapping(target = "images", ignore = true)
    void updateProduct(@MappingTarget Product product, ProductUpdateRequest request);

}