package com.ecommerce.platform.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    @NotBlank
    @Size(min = 4, message = "USERNAME_INVALID")
    String username;

    @NotBlank
    @Size(min = 6, message = "PASSWORD_INVALID")
    String password;

    String firstName;
    String lastName;
    LocalDate dob;
    String city;
}
