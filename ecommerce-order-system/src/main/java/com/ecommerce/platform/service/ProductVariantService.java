package com.ecommerce.platform.service;

import com.ecommerce.platform.dto.request.ProductVariantCreateRequest;
import com.ecommerce.platform.dto.response.ProductVariantResponse;
import com.ecommerce.platform.entity.Product;
import com.ecommerce.platform.entity.ProductVariant;
import com.ecommerce.platform.exception.AppException;
import com.ecommerce.platform.exception.ErrorCode;
import com.ecommerce.platform.mapper.ProductVariantMapper;
import com.ecommerce.platform.repository.ProductRepository;
import com.ecommerce.platform.repository.ProductVariantRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductVariantService {


    ProductRepository productRepository;
    ProductVariantRepository productVariantRepository;
    ProductVariantMapper productVariantMapper;

    public ProductVariantResponse createProductVariant(ProductVariantCreateRequest request, String productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        var productVariant = productVariantMapper.toProductVariant(request);

        productVariant.setProduct(product);

        return productVariantMapper.toProductVariantResponse(
                productVariantRepository.save(productVariant)
        );
    }

    public ProductVariantResponse updateProductVariant(ProductVariantCreateRequest request, String productVariantId) {
        ProductVariant productVariant = productVariantRepository.findById(productVariantId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_VARIANT_NOT_FOUND));

        productVariantMapper.updateProductVariant(productVariant, request);

        return productVariantMapper.toProductVariantResponse(
                productVariantRepository.save(productVariant)
        );
    }

    public void deleteProductVariant(String productVariantId){
        productVariantRepository.deleteById(productVariantId);
    }

    public ProductVariantResponse getProductVariant(String productVariantId){
        ProductVariant productVariant = productVariantRepository.findById(productVariantId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_VARIANT_NOT_FOUND));

        return productVariantMapper.toProductVariantResponse(productVariant);
    }

}
