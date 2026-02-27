package com.ecommerce.platform.service;

import com.ecommerce.platform.dto.request.PermissionRequest;
import com.ecommerce.platform.dto.request.RoleRequest;
import com.ecommerce.platform.dto.response.PermissionResponse;
import com.ecommerce.platform.dto.response.RoleResponse;
import com.ecommerce.platform.entity.Permission;
import com.ecommerce.platform.entity.Role;
import com.ecommerce.platform.mapper.PermissionMapper;
import com.ecommerce.platform.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }
}
