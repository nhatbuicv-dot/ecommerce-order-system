package com.ecommerce.platform.service;

import com.ecommerce.platform.configuration.SecurityUtils;
import com.ecommerce.platform.dto.request.OrderRequest;
import com.ecommerce.platform.dto.response.OrderItemResponse;
import com.ecommerce.platform.dto.response.OrderResponse;
import com.ecommerce.platform.entity.*;
import com.ecommerce.platform.enums.OrderStatus;
import com.ecommerce.platform.exception.AppException;
import com.ecommerce.platform.exception.ErrorCode;
import com.ecommerce.platform.mapper.OrderItemMapper;
import com.ecommerce.platform.repository.CartRepository;
import com.ecommerce.platform.repository.OrderRepository;
import com.ecommerce.platform.repository.ProductVariantRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderService {

    private final SecurityUtils securityUtils;
    private final CartRepository cartRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderRepository orderRepository;
    private final ProductVariantRepository productVariantRepository;

    private Double calculateTotalPrice(Order order) {
        return order.getOrderItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {
        User user = securityUtils.getCurrentUser();

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));

        if (cart.getCartItems().isEmpty()) {
            throw new AppException(ErrorCode.CART_EMPTY);
        }

        var ship = orderRequest.getShippingAddress();

        // tạo Order
        Order order = new Order();
        order.setUser(user);

        order.setShippingAddress(ship);

        List<OrderItem> orderItems = cart.getCartItems().stream().map(cartItem -> {
            var variant = cartItem.getProductVariant();

            // check tồn kho
            if (variant.getQuantity() < cartItem.getQuantity()) {
                throw new AppException(ErrorCode.OUT_OF_STOCK);
            }

            // trừ tồn kho
            variant.setQuantity(variant.getQuantity() - cartItem.getQuantity());

            // chuyển cartItem sang orderItem , lưu orderId vào orderItem , lưu giá
            OrderItem item = orderItemMapper.toOrderItem(cartItem);
            item.setOrder(order);
            item.setPrice(variant.getPrice());

            return item;
        }).toList();

        // gắn orderItem vào order
        order.setOrderItems(orderItems);


        Double total = calculateTotalPrice(order);
        order.setTotalPrice(total);

        orderRepository.save(order);

        // Clear cart
        cart.getCartItems().clear();
        cartRepository.save(cart);

        return OrderResponse.builder()
                .id(order.getId())
                .userId(user.getId())
                .createdAt(order.getCreatedAt())
                .orderItems(orderItemMapper.toOrderItemResponse(orderItems))
                .total(total)
                .status(order.getOrderStatus().toString())
                .shippingAddress(ship)
                .build();
    }

    public OrderResponse getOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

        List<OrderItemResponse> itemResponses = order.getOrderItems().stream()
                .map(item -> OrderItemResponse.builder()
                        .productName(item.getProductVariant().getProduct().getName()) // Lấy tên từ quan hệ
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .build())
                .toList();

        return OrderResponse.builder()
                .id(order.getId())
                .orderItems(itemResponses)
                .total(order.getTotalPrice())
                .build();
    }

    // admin update status
    @PreAuthorize("hasRole('ADMIN')")
    public OrderResponse updateStatusOrder(String orderId , OrderStatus orderStatus){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

        if(order.getOrderStatus() == OrderStatus.CANCELLED || order.getOrderStatus() == OrderStatus.COMPLETED ){
            throw new AppException(ErrorCode.ORDER_STATUS_NOT_ALLOWED_UPDATE);
        }

        if (orderStatus == OrderStatus.CANCELLED) {
            handleReturnStock(order);
        }

        order.setOrderStatus(orderStatus);
        orderRepository.save(order);

        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .createdAt(order.getCreatedAt())
                .status(order.getOrderStatus().toString())
                .total(order.getTotalPrice())
                .build();
    }

    // user hủy order
    @Transactional
    public void cancelOrder (String orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        if (order.getOrderStatus() == OrderStatus.SHIPPING ||
                order.getOrderStatus() == OrderStatus.COMPLETED ||
                order.getOrderStatus() == OrderStatus.CANCELLED) {
            throw new AppException(ErrorCode.ORDER_STATUS_NOT_ALLOWED_UPDATE);
        }
        order.setOrderStatus(OrderStatus.CANCELLED);
        handleReturnStock(order);
        orderRepository.save(order);
    }

    // cộng lại tồn kho
    public void handleReturnStock(Order order){
        for(OrderItem orderItem: order.getOrderItems()){
            ProductVariant productVariant = orderItem.getProductVariant();

            if(productVariant != null){
                int restoredQuantity = productVariant.getQuantity() + orderItem.getQuantity();
                productVariant.setQuantity(restoredQuantity);

                productVariantRepository.save(productVariant);
            }
        }
    }

}
