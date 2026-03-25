package com.ecommerce.platform.controller;

import com.ecommerce.platform.dto.request.OrderRequest;
import com.ecommerce.platform.dto.response.ApiResponse;
import com.ecommerce.platform.dto.response.OrderResponse;
import com.ecommerce.platform.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    ApiResponse<OrderResponse> create (@RequestBody OrderRequest orderRequest) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.createOrder(orderRequest))
                .build();
    }

    @GetMapping("/{orderId}")
    ApiResponse<OrderResponse> get (@PathVariable String orderId) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.getOrder(orderId))
                .build();
    }


}
