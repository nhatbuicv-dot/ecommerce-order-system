package com.ecommerce.platform.mapper;

import com.ecommerce.platform.dto.request.UserRequest;
import com.ecommerce.platform.dto.request.UserUpdate;
import com.ecommerce.platform.dto.response.UserResponse;
import com.ecommerce.platform.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toUser(UserRequest userRequest);

    UserResponse toUserResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "username", ignore = true)
    void updateUser (UserUpdate userUpdate, @MappingTarget User user);
}
