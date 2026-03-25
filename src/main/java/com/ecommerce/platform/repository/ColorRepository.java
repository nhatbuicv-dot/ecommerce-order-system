package com.ecommerce.platform.repository;

import com.ecommerce.platform.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, String> {
}
