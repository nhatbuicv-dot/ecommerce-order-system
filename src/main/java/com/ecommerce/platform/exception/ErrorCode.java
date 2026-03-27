package com.ecommerce.platform.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    //SYSTEM / COMMON ERROR
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),

    //USER MODULE
    USER_ALREADY_EXISTED(1002, "User already  existed", HttpStatus.CONFLICT),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID_FORMAT(1009, "Username invalid format", HttpStatus.BAD_REQUEST),

    //CATEGORY MODULE
    CATEGORY_ALREADY_EXISTS(1010, "Category already existed", HttpStatus.CONFLICT),
    CATEGORY_NOT_FOUND(1011, "Category not found", HttpStatus.NOT_FOUND),

    //PRODUCT MODULE
    PRODUCT_ALREADY_EXISTS(1012, "Product already existed", HttpStatus.CONFLICT),
    PRODUCT_NOT_FOUND(1013, "Product not found", HttpStatus.NOT_FOUND),
    PRODUCT_VARIANT_NOT_FOUND(1014, "ProductVariant not found", HttpStatus.NOT_FOUND),
    IMAGE_NOT_FOUND(1015, "Image not found", HttpStatus.NOT_FOUND),
    COLOR_NOT_FOUND(1016, "Color not found", HttpStatus.NOT_FOUND),

    //CART MODULE
    CART_ITEM_NOT_FOUND(1017, "Cart item not found", HttpStatus.NOT_FOUND),
    CART_NOT_FOUND(1018, "Cart not found", HttpStatus.NOT_FOUND),
    CART_EMPTY(1019, "Cart are empty", HttpStatus.BAD_REQUEST),
    OUT_OF_STOCK(1020, "Product is out of stock", HttpStatus.BAD_REQUEST),

    //ORDER MODULE
    ORDER_NOT_FOUND(1021, "Order not found", HttpStatus.NOT_FOUND),
    ORDER_STATUS_NOT_ALLOWED_UPDATE(1022, "Cannot update the status in the current state.", HttpStatus.CONFLICT),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
