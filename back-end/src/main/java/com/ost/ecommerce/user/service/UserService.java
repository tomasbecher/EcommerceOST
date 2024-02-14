package com.ost.ecommerce.user.service;

import com.ost.ecommerce.user.repository.entity.User;
import com.ost.ecommerce.user.service.domain.UserCreate;
import com.ost.ecommerce.user.service.domain.UserProfile;

import java.util.Optional;

public interface UserService {

    User create(UserCreate userCreate);
    Optional<User> findByUsername(String username);
    User findOrFail(Integer userId);
    UserProfile getUserProfile();

}
