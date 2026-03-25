package com.ecommerce.platform.mapper;

import com.ecommerce.platform.dto.request.PermissionRequest;
import com.ecommerce.platform.dto.response.PermissionResponse;
import com.ecommerce.platform.entity.Permission;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
