package com.ecommerce.platform.service;

import com.ecommerce.platform.dto.request.UserRequest;
import com.ecommerce.platform.dto.request.UserUpdate;
import com.ecommerce.platform.dto.response.UserResponse;
import com.ecommerce.platform.entity.User;
import com.ecommerce.platform.exception.AppException;
import com.ecommerce.platform.exception.ErrorCode;
import com.ecommerce.platform.mapper.UserMapper;
import com.ecommerce.platform.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserRequest userRequest) {
        if(userRepository.existsByUsername(userRequest.getUsername())) throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(userRequest);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user = userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(UserUpdate userUpdate, String userId) {
        User user= userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(userUpdate ,user);

        return userMapper.toUserResponse(userRepository.save(user));

    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    public List<UserResponse> getUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toUserResponse).toList();
    }
}
