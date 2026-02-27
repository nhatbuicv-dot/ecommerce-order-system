package com.ecommerce.platform.mapper;

import com.ecommerce.platform.dto.request.UserRequest;
import com.ecommerce.platform.dto.request.UserUpdate;
import com.ecommerce.platform.dto.response.UserResponse;
import com.ecommerce.platform.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest userRequest);
    UserResponse toUserResponse(User user);
    @Mapping(target = "roles", ignore = true)
    void updateUser (UserUpdate userUpdate, @MappingTarget User user);
}
