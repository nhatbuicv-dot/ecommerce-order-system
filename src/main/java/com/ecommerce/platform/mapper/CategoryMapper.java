package com.ecommerce.platform.mapper;

import com.ecommerce.platform.dto.request.CategoryRequest;
import com.ecommerce.platform.dto.response.CategoryResponse;
import com.ecommerce.platform.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "products" , ignore = true)
    Category toCategory(CategoryRequest categoryRequest);


    @Mapping(target = "productCount" , ignore = true)
    CategoryResponse toCategoryResponse(Category category);

    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "products" , ignore = true)
    void updateCategory(@MappingTarget Category category, CategoryRequest request);
}
