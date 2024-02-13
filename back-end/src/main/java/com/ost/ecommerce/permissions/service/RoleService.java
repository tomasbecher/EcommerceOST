package com.ost.ecommerce.permissions.service;

import com.ost.ecommerce.permissions.repository.entity.Role;
import com.ost.ecommerce.permissions.repository.entity.UserRole;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Optional<Role> findByName(String name);
    UserRole saveUserRole(UserRole userRole);
    List<Role> findAllByUserId(Integer userId);
}
