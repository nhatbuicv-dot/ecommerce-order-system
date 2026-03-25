package com.ecommerce.platform.repository;

import com.ecommerce.platform.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
