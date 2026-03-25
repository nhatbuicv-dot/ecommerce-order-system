package com.ecommerce.platform.mapper;

import com.ecommerce.platform.dto.request.ColorRequest;
import com.ecommerce.platform.dto.request.ProductVariantCreateRequest;
import com.ecommerce.platform.dto.response.ColorResponse;
import com.ecommerce.platform.dto.response.ProductVariantResponse;
import com.ecommerce.platform.entity.Color;
import com.ecommerce.platform.entity.ProductVariant;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-25T09:33:39+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class ProductVariantMapperImpl implements ProductVariantMapper {

    @Override
    public ProductVariantResponse toProductVariantResponse(ProductVariant productVariant) {
        if ( productVariant == null ) {
            return null;
        }

        ProductVariantResponse.ProductVariantResponseBuilder productVariantResponse = ProductVariantResponse.builder();

        productVariantResponse.color( colorToColorResponse( productVariant.getColor() ) );
        productVariantResponse.id( productVariant.getId() );
        productVariantResponse.size( productVariant.getSize() );
        productVariantResponse.price( productVariant.getPrice() );
        productVariantResponse.quantity( productVariant.getQuantity() );

        return productVariantResponse.build();
    }

    @Override
    public ProductVariant toProductVariant(ProductVariantCreateRequest variantAddRequest) {
        if ( variantAddRequest == null ) {
            return null;
        }

        ProductVariant.ProductVariantBuilder productVariant = ProductVariant.builder();

        productVariant.color( colorRequestToColor( variantAddRequest.getColor() ) );
        productVariant.size( variantAddRequest.getSize() );
        productVariant.quantity( variantAddRequest.getQuantity() );
        productVariant.price( variantAddRequest.getPrice() );

        return productVariant.build();
    }

    @Override
    public void updateProductVariant(ProductVariant productVariant, ProductVariantCreateRequest request) {
        if ( request == null ) {
            return;
        }

        if ( request.getColor() != null ) {
            if ( productVariant.getColor() == null ) {
                productVariant.setColor( Color.builder().build() );
            }
            colorRequestToColor1( request.getColor(), productVariant.getColor() );
        }
        else {
            productVariant.setColor( null );
        }
        productVariant.setSize( request.getSize() );
        productVariant.setQuantity( request.getQuantity() );
        productVariant.setPrice( request.getPrice() );
    }

    protected ColorResponse colorToColorResponse(Color color) {
        if ( color == null ) {
            return null;
        }

        ColorResponse.ColorResponseBuilder colorResponse = ColorResponse.builder();

        colorResponse.id( color.getId() );
        colorResponse.name( color.getName() );
        colorResponse.code( color.getCode() );

        return colorResponse.build();
    }

    protected Color colorRequestToColor(ColorRequest colorRequest) {
        if ( colorRequest == null ) {
            return null;
        }

        Color.ColorBuilder color = Color.builder();

        color.id( colorRequest.getId() );

        return color.build();
    }

    protected void colorRequestToColor1(ColorRequest colorRequest, Color mappingTarget) {
        if ( colorRequest == null ) {
            return;
        }

        mappingTarget.setId( colorRequest.getId() );
    }
}
