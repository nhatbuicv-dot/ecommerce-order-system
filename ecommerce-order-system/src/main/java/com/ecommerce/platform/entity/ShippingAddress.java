package com.ecommerce.platform.entity;

import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShippingAddress {
    String firstName;
    String lastName;
    String phone;
    String address;
    String email;
}
