package com.ecommerce.platform.controller;

import com.ecommerce.platform.dto.request.CartItemRequest;
import com.ecommerce.platform.dto.response.ApiResponse;
import com.ecommerce.platform.dto.response.CartItemResponse;
import com.ecommerce.platform.service.CartItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartItem")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CartItemController {

    CartItemService cartItemService;

    @PostMapping
    ApiResponse<CartItemResponse> upsert(@RequestBody CartItemRequest cartItemRequest) {
        return ApiResponse.<CartItemResponse>builder()
                .result(cartItemService.upsertCartItem(cartItemRequest))
                .build();
    }
}
