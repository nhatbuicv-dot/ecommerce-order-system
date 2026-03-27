package com.ecommerce.platform.enums;

public enum OrderStatus {
    PENDING,    // Đơn mới
    CONFIRMED,  // Đã xác nhận
    SHIPPING,   // Đang giao
    COMPLETED,  // Thành công (Tính vào doanh thu)
    CANCELLED   // Đã hủy
}
