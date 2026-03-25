package com.ecommerce.platform.service;

import com.ecommerce.platform.configuration.SecurityUtils;
import com.ecommerce.platform.dto.request.OrderRequest;
import com.ecommerce.platform.dto.response.OrderItemResponse;
import com.ecommerce.platform.dto.response.OrderResponse;
import com.ecommerce.platform.entity.Cart;
import com.ecommerce.platform.entity.Order;
import com.ecommerce.platform.entity.OrderItem;
import com.ecommerce.platform.entity.User;
import com.ecommerce.platform.exception.AppException;
import com.ecommerce.platform.exception.ErrorCode;
import com.ecommerce.platform.mapper.OrderItemMapper;
import com.ecommerce.platform.repository.CartRepository;
import com.ecommerce.platform.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
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
                .createdAt(order.getCreatedAt())
                .orderItems(orderItemMapper.toOrderItemResponse(orderItems))
                .total(total)
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

}
