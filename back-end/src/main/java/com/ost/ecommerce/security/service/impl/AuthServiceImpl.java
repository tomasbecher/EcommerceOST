package com.ost.ecommerce.security.service.impl;

import com.ost.ecommerce.security.repository.RefreshTokenRepository;
import com.ost.ecommerce.security.repository.entity.RefreshToken;
import com.ost.ecommerce.security.service.AuthService;
import com.ost.ecommerce.security.service.JwtService;
import com.ost.ecommerce.security.service.domain.JwtResponse;
import com.ost.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;

    @Override
    public JwtResponse refreshToken(String token) {
        return findByToken(token)
                .map(this::verifyExpiration)
                .map(RefreshToken::getUserId)
                .map(userId -> {
                    String username = userService.findOrFail(userId).getUsername();
                    String accessToken = jwtService.generateToken(username);
                    return JwtResponse.builder()
                            .accessToken(accessToken)
                            .token(token)
                            .username(username)
                            .build();
                }).orElseThrow(() -> new RuntimeException(
                                "Refresh token is not in database."));
    }

    @Override
    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(userService.findByUsername(username).get().getId())
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))//10
                .build();
        return refreshTokenRepository.save(refreshToken);
    }


    private Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    private RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token was expired. Please make a new signin request");
        }
        return token;
    }


}
