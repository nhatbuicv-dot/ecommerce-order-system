package com.ecommerce.platform.repository;

import com.ecommerce.platform.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, String> {
}
