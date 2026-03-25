package com.ecommerce.platform.service;

import com.ecommerce.platform.dto.request.ProductImageRequest;
import com.ecommerce.platform.dto.response.ProductImageResponse;
import com.ecommerce.platform.entity.Product;
import com.ecommerce.platform.exception.AppException;
import com.ecommerce.platform.exception.ErrorCode;
import com.ecommerce.platform.mapper.ProductImageMapper;
import com.ecommerce.platform.repository.ProductImageRepository;
import com.ecommerce.platform.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductImageService {
    ProductImageRepository productImageRepository;
    ProductRepository productRepository;
    ProductImageMapper productImageMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public ProductImageResponse create(ProductImageRequest request, String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        var productImage = productImageMapper.toProductImage(request);
        productImage.setProduct(product);

        return productImageMapper.toProductImageResponse(
                productImageRepository.save(productImage)
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void delete(String imageId) {
        if (!productImageRepository.existsById(imageId)) {
            throw new AppException(ErrorCode.IMAGE_NOT_FOUND);
        }
        productImageRepository.deleteById(imageId);
    }
}
