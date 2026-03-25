package com.ecommerce.platform.configuration;

import com.ecommerce.platform.entity.User;
import com.ecommerce.platform.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityUtils {
    UserRepository userRepository;
    public  String getCurrentUserId() {

        var authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        return authentication.getName();
    }

    public User getCurrentUser() {
        var authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String userId = authentication.getName();

        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


}
