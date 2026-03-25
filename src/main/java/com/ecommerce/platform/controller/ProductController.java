package com.ecommerce.platform.controller;

import com.ecommerce.platform.dto.request.ProductCreateRequest;
import com.ecommerce.platform.dto.request.ProductUpdateRequest;
import com.ecommerce.platform.dto.response.ApiResponse;
import com.ecommerce.platform.dto.response.ProductListResponse;
import com.ecommerce.platform.dto.response.ProductResponse;
import com.ecommerce.platform.service.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductController {

    ProductService productService;

    @PostMapping
    ApiResponse<ProductResponse> createProduct (@RequestBody ProductCreateRequest productCreateRequest) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.createProduct(productCreateRequest))
                .build();
    }

    @PostMapping("/list")
    ApiResponse<List<ProductResponse>> createListProduct (@RequestBody List<ProductCreateRequest> productCreateRequest) {
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.createListProduct(productCreateRequest))
                .build();
    }

    @PutMapping("/{productId}")
    ApiResponse<ProductResponse> updateProduct (@RequestBody @Valid ProductUpdateRequest productUpdateRequest , @PathVariable String productId) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.updateProduct(productUpdateRequest, productId))
                .build();
    }


    @GetMapping("/{productId}")
    ApiResponse<ProductResponse> getProduct (@PathVariable String productId) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.getProduct(productId))
                .build();
    }

    @DeleteMapping("/{productId}")
    ApiResponse<String> deleteProduct (@PathVariable String productId) {
        productService.deleteProduct(productId);
        return ApiResponse.<String>builder()
                .result("Product has been deleted")
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<Page<ProductListResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortField,
            @RequestParam(defaultValue = "ASC") String direction
    ) {
        return ApiResponse.<Page<ProductListResponse>>builder()
                .result(productService.getProducts(page, size, sortField, direction))
                .build();
    }
}
