package com.ecommerce.platform.mapper;

import com.ecommerce.platform.dto.request.ProductCreateRequest;
import com.ecommerce.platform.dto.request.ProductUpdateRequest;
import com.ecommerce.platform.dto.response.ProductListResponse;
import com.ecommerce.platform.dto.response.ProductResponse;
import com.ecommerce.platform.entity.Product;
import com.ecommerce.platform.entity.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {ProductVariantMapper.class, ReviewMapper.class}
)
public interface ProductMapper {

    @Mapping(target = "categoryId", source = "category.id")
    ProductResponse toProductResponse(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "variants", ignore = true)
    @Mapping(target = "images", ignore = true)
    Product toProduct(ProductCreateRequest request);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "variants", ignore = true)
    @Mapping(target = "images", ignore = true)
    void updateProduct(@MappingTarget Product product, ProductUpdateRequest request);

}