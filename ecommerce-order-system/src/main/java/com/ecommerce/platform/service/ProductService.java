package com.ecommerce.platform.service;

import com.ecommerce.platform.dto.request.ProductCreateRequest;
import com.ecommerce.platform.dto.request.ProductUpdateRequest;
import com.ecommerce.platform.dto.request.ProductVariantCreateRequest;
import com.ecommerce.platform.dto.response.ProductListResponse;
import com.ecommerce.platform.dto.response.ProductResponse;
import com.ecommerce.platform.entity.Category;
import com.ecommerce.platform.entity.Product;
import com.ecommerce.platform.entity.ProductImage;
import com.ecommerce.platform.entity.ProductVariant;
import com.ecommerce.platform.exception.AppException;
import com.ecommerce.platform.exception.ErrorCode;
import com.ecommerce.platform.mapper.ProductMapper;
import com.ecommerce.platform.mapper.ProductVariantMapper;
import com.ecommerce.platform.repository.CategoryRepository;
import com.ecommerce.platform.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductService {

    ProductRepository productRepository;
    ProductMapper productMapper;
    CategoryRepository categoryRepository;
    ProductVariantMapper productVariantMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public ProductResponse createProduct(ProductCreateRequest productCreateRequest) {
        if (productRepository.findByName(productCreateRequest.getName()).isPresent()) {
            throw new AppException(ErrorCode.PRODUCT_ALREADY_EXISTS);
        }

        Category category = categoryRepository.findById(productCreateRequest.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        var product = productMapper.toProduct(productCreateRequest);

        product.setCategory(category);

        if (productCreateRequest.getVariants() != null) {
            for (ProductVariantCreateRequest productVariantCreateRequest : productCreateRequest.getVariants()) {
                ProductVariant variant = productVariantMapper.toProductVariant(productVariantCreateRequest);
                product.addVariant(variant);
                product.recalculatePrice();
            }
        }

        // images
        if (productCreateRequest.getImages() != null) {
            for (String url : productCreateRequest.getImages()) {
                ProductImage image = new ProductImage();
                image.setImageUrl(url);
                product.addImage(image);
            }
        }

        productRepository.save(product);

        return productMapper.toProductResponse(product);
    }



    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public List<ProductResponse> createListProduct(List<ProductCreateRequest> requests) {

        List<ProductResponse> responses = new ArrayList<>();

        for (ProductCreateRequest request : requests) {

            if (productRepository.findByName(request.getName()).isPresent()) {
                throw new AppException(ErrorCode.PRODUCT_ALREADY_EXISTS);
            }

            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

            Product product = productMapper.toProduct(request);

            product.setCategory(category);

            // variants
            if (request.getVariants() != null) {
                for (ProductVariantCreateRequest variantRequest : request.getVariants()) {
                    ProductVariant variant = productVariantMapper.toProductVariant(variantRequest);
                    product.addVariant(variant);
                    product.recalculatePrice();
                }
            }

            // images
            if (request.getImages() != null) {
                for (String url : request.getImages()) {
                    ProductImage image = new ProductImage();
                    image.setImageUrl(url);
                    product.addImage(image);
                }
            }

            productRepository.save(product);

            responses.add(productMapper.toProductResponse(product));
        }

        return responses;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public ProductResponse updateProduct(ProductUpdateRequest request, String productId) {
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

            product.setCategory(category);
        }


        productMapper.updateProduct(product, request);

        if (request.getVariants() != null) {
            product.getVariants().clear();
            for (var variantRequest : request.getVariants()) {
                ProductVariant variant = productVariantMapper.toProductVariant(variantRequest);
                product.addVariant(variant);
            }
        }

        if (request.getImages() != null) {
            product.getImages().clear();
            for (String url : request.getImages()) {
                ProductImage image = new ProductImage();
                image.setImageUrl(url);
                product.addImage(image);
            }
            product.recalculatePrice();
        }

        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Transactional
    public void deleteProduct(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        product.recalculatePrice();
        productRepository.deleteById(productId);
    }

    public ProductResponse getProduct(String productId) {
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        return productMapper.toProductResponse(product);
    }

    public Page<ProductListResponse> getProducts(int page, int size, String sortField, String direction) {
        Sort sort = Sort.by(direction.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC, sortField);
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.getProductList(pageable);
    }
}