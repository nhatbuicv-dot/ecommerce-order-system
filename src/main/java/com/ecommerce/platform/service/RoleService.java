package com.ecommerce.platform.service;

import com.ecommerce.platform.dto.request.RoleRequest;
import com.ecommerce.platform.dto.response.RoleResponse;
import com.ecommerce.platform.entity.Role;
import com.ecommerce.platform.mapper.RoleMapper;
import com.ecommerce.platform.repository.PermissionRepository;
import com.ecommerce.platform.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;
    RoleRepository roleRepository;

    public RoleResponse createRole(RoleRequest roleRequest) {
        var role = roleMapper.toRole(roleRequest);
        var permission = permissionRepository.findAllById(roleRequest.getPermissions());

        role.setPermissions(new HashSet<>(permission));
        roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(role -> roleMapper.toRoleResponse(role)).toList();
    }

    public void deleteRole(String roleId) {
        roleRepository.deleteById(roleId);
    }
}
