package com.ecommerce.platform.mapper;

import com.ecommerce.platform.dto.request.ProductCreateRequest;
import com.ecommerce.platform.dto.request.ProductUpdateRequest;
import com.ecommerce.platform.dto.response.ProductImageResponse;
import com.ecommerce.platform.dto.response.ProductResponse;
import com.ecommerce.platform.dto.response.ProductVariantResponse;
import com.ecommerce.platform.entity.Product;
import com.ecommerce.platform.entity.ProductImage;
import com.ecommerce.platform.entity.ProductVariant;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-25T09:14:31+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private ProductVariantMapper productVariantMapper;

    @Override
    public ProductResponse toProductResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse.ProductResponseBuilder productResponse = ProductResponse.builder();

        productResponse.variants( productVariantListToProductVariantResponseList( product.getVariants() ) );
        productResponse.id( product.getId() );
        productResponse.name( product.getName() );
        productResponse.brand( product.getBrand() );
        productResponse.description( product.getDescription() );
        productResponse.minPrice( product.getMinPrice() );
        productResponse.maxPrice( product.getMaxPrice() );
        productResponse.images( productImageListToProductImageResponseList( product.getImages() ) );

        return productResponse.build();
    }

    @Override
    public Product toProduct(ProductCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( request.getName() );
        product.brand( request.getBrand() );
        product.description( request.getDescription() );

        return product.build();
    }

    @Override
    public void updateProduct(Product product, ProductUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        product.setName( request.getName() );
        product.setBrand( request.getBrand() );
        product.setDescription( request.getDescription() );
    }

    protected List<ProductVariantResponse> productVariantListToProductVariantResponseList(List<ProductVariant> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductVariantResponse> list1 = new ArrayList<ProductVariantResponse>( list.size() );
        for ( ProductVariant productVariant : list ) {
            list1.add( productVariantMapper.toProductVariantResponse( productVariant ) );
        }

        return list1;
    }

    protected ProductImageResponse productImageToProductImageResponse(ProductImage productImage) {
        if ( productImage == null ) {
            return null;
        }

        ProductImageResponse.ProductImageResponseBuilder productImageResponse = ProductImageResponse.builder();

        productImageResponse.id( productImage.getId() );
        productImageResponse.imageUrl( productImage.getImageUrl() );

        return productImageResponse.build();
    }

    protected List<ProductImageResponse> productImageListToProductImageResponseList(List<ProductImage> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductImageResponse> list1 = new ArrayList<ProductImageResponse>( list.size() );
        for ( ProductImage productImage : list ) {
            list1.add( productImageToProductImageResponse( productImage ) );
        }

        return list1;
    }
}
