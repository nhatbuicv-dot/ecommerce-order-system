package com.ecommerce.platform.controller;

import com.ecommerce.platform.dto.request.CategoryRequest;
import com.ecommerce.platform.dto.response.ApiResponse;
import com.ecommerce.platform.dto.response.CategoryResponse;
import com.ecommerce.platform.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CategoryController {
    CategoryService categoryService;

    @PostMapping
    public ApiResponse<CategoryResponse> create (@RequestBody CategoryRequest categoryRequest) {
        var result = categoryService.createCategory(categoryRequest);

        return ApiResponse.<CategoryResponse>builder()
                .result(result)
                .build();
    }

    @PutMapping("/{categoryId}")
    public ApiResponse<CategoryResponse> update (@RequestBody CategoryRequest categoryRequest,@PathVariable("categoryId")  String categoryId) {
        var result = categoryService.updateCategory(categoryRequest,categoryId);

        return ApiResponse.<CategoryResponse>builder()
                .result(result)
                .build();
    }

    @GetMapping
    public ApiResponse<List<CategoryResponse>> get () {
        var result = categoryService.getCategories();

        return ApiResponse.<List<CategoryResponse>>builder()
                .result(result)
                .build();
    }

    @GetMapping("/{categoryId}")
    public ApiResponse<CategoryResponse> get (@PathVariable("categoryId")  String categoryId) {
        var result = categoryService.getCategory(categoryId);

        return ApiResponse.<CategoryResponse>builder()
                .result(result)
                .build();
    }


    @DeleteMapping("/{categoryId}")
    public ApiResponse<CategoryResponse> delete (@PathVariable("categoryId")  String categoryId) {

        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.deleteCategory(categoryId))
                .build();
    }
}
