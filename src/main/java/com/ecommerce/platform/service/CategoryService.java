package com.ecommerce.platform.service;

import com.ecommerce.platform.dto.request.CategoryRequest;
import com.ecommerce.platform.dto.response.ApiResponse;
import com.ecommerce.platform.dto.response.CategoryResponse;
import com.ecommerce.platform.entity.Category;
import com.ecommerce.platform.exception.AppException;
import com.ecommerce.platform.exception.ErrorCode;
import com.ecommerce.platform.mapper.CategoryMapper;
import com.ecommerce.platform.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        if (categoryRepository.existsById(categoryRequest.getName()))
            throw new AppException(ErrorCode.CATEGORY_ALREADY_EXISTS);

        Category category = categoryMapper.toCategory(categoryRequest);
        category = categoryRepository.save(category);

        return categoryMapper.toCategoryResponse(category);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponse updateCategory(CategoryRequest categoryRequest ,String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        categoryMapper.updateCategory(category,categoryRequest);

        categoryRepository.save(category);
        return CategoryResponse.builder()
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponse deleteCategory(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        categoryRepository.deleteById(categoryId);
        return CategoryResponse.builder().build();
    }

    public List<CategoryResponse> getCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toCategoryResponse).toList();
    }

    public CategoryResponse getCategory(String categoryId) {
        Category category =categoryRepository.findById(categoryId).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        return categoryMapper.toCategoryResponse(category);
    }

}
