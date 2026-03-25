package com.ecommerce.platform.controller;

import com.ecommerce.platform.dto.request.ProductVariantCreateRequest;
import com.ecommerce.platform.dto.response.ApiResponse;
import com.ecommerce.platform.dto.response.ProductVariantResponse;
import com.ecommerce.platform.service.ProductVariantService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/variant")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    @PostMapping("/{productVariantId}")
    ApiResponse<ProductVariantResponse> create (@RequestBody ProductVariantCreateRequest productVariantCreateRequest , @PathVariable String productVariantId) {
        return  ApiResponse.<ProductVariantResponse>builder()
                .result(productVariantService.createProductVariant(productVariantCreateRequest, productVariantId))
                .build();
    }

    @GetMapping("/{productVariantId}")
    ApiResponse<ProductVariantResponse> getProductVariant(@PathVariable String productVariantId) {
        return ApiResponse.<ProductVariantResponse>builder()
                .result(productVariantService.getProductVariant(productVariantId))
                .build();
    }

    @PutMapping("/{productVariantId}")
    ApiResponse<ProductVariantResponse> update(@PathVariable String productVariantId,
                                               @RequestBody ProductVariantCreateRequest request) {
        return ApiResponse.<ProductVariantResponse>builder()
                .result(productVariantService.updateProductVariant(request, productVariantId))
                .build();
    }

    @DeleteMapping("/{productVariantId}")
    ApiResponse<String> delete(@PathVariable String productVariantId) {
        productVariantService.deleteProductVariant(productVariantId);
        return ApiResponse.<String>builder()
                .result("Product variant has been deleted")
                .build();
    }


}
