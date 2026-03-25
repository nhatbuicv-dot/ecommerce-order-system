# Ecommerce Order System - Software Quality Assurance Project

Dự án Hệ thống Quản lý Đơn hàng Thương mại điện tử, được phát triển để thực hành các nguyên tắc về Đảm bảo chất lượng phần mềm (SQA).

## 📂 Cấu trúc dự án
- `/ecommerce-order-system`: Mã nguồn Backend (Java Spring Boot).
- `/database`: Chứa file backup cơ sở dữ liệu MySQL (.sql).

## 🛠 Công nghệ sử dụng
- **Backend:** Java 21, Spring Boot 3, Spring Data JPA, MySQL.
- **Testing:** k6 (Performance/Load Testing), JUnit 5.
- **Tools:** Maven, MapStruct, Lombok.
## 🚀 Hướng dẫn cài đặt & Chạy
1. **Database:** Import file SQL trong thư mục `/database` vào MySQL Server của bạn.
2. **Cấu hình:** Chỉnh sửa `application.properties` trong `src/main/resources` để khớp với thông tin MySQL của bạn.