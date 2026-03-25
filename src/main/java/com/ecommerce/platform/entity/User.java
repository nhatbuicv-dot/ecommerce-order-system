package com.ecommerce.platform.entity;

import jakarta.persistence.*;
        import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
        import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(unique = true,nullable = false)
    String username;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    List<Order> orders = new ArrayList<>();


    @Column(nullable = false)
    String password;
    String firstName;
    String lastName;
    LocalDate dob;
    String city;
    @ManyToMany
    Set<Role> roles;
}
