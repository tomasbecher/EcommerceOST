package com.ost.ecommerce.permissions.repository;

import com.ost.ecommerce.permissions.repository.entity.UserRole;
import com.ost.ecommerce.permissions.repository.entity.UserRolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePK> {

    List<UserRole> findAllByUserRolePK_UserId(Integer userId);
}
