package com.ost.ecommerce.permissions.service.impl;

import com.ost.ecommerce.permissions.repository.RoleRepository;
import com.ost.ecommerce.permissions.repository.UserRoleRepository;
import com.ost.ecommerce.permissions.repository.entity.Role;
import com.ost.ecommerce.permissions.repository.entity.UserRole;
import com.ost.ecommerce.permissions.repository.entity.UserRolePK;
import com.ost.ecommerce.permissions.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public UserRole saveUserRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public List<Role> findAllByUserId(Integer userId) {
        return userRoleRepository.findAllByUserRolePK_UserId(userId)
                .stream().map(UserRole::getUserRolePK)
                .map(UserRolePK::getRoleId)
                .distinct()
                .map(this::findRoleById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<Role> findRoleById(Short roleId){
        return roleRepository.findById(roleId);
    }

}
