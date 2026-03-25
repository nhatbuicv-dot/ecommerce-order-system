package com.ecommerce.platform.mapper;

import com.ecommerce.platform.dto.request.RoleRequest;
import com.ecommerce.platform.dto.response.RoleResponse;
import com.ecommerce.platform.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
