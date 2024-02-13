package com.ost.ecommerce.security.service;

import com.ost.ecommerce.security.repository.entity.RefreshToken;
import com.ost.ecommerce.security.service.domain.JwtResponse;

public interface AuthService {

    JwtResponse refreshToken(String token);
    RefreshToken createRefreshToken(String username);

}
