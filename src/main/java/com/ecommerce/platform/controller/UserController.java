package com.ecommerce.platform.controller;

import com.ecommerce.platform.dto.request.UserRequest;
import com.ecommerce.platform.dto.request.UserUpdate;
import com.ecommerce.platform.dto.response.ApiResponse;
import com.ecommerce.platform.dto.response.UserResponse;
import com.ecommerce.platform.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(userRequest))
                .build();
    }

    @PutMapping("/update/{userId}")
    public ApiResponse<UserResponse> updateUser(@RequestBody @Valid UserUpdate userUpdate, @PathVariable String userId) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userUpdate,userId))
                .build();
    }

    @DeleteMapping("/delete/{userId}")
    public ApiResponse<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);

        return ApiResponse.<String>builder()
                .result("User has been deleted")
                .build();
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/my-info")
    public ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

}
