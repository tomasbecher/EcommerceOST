package com.ost.ecommerce.security.controller;

import com.ost.ecommerce.security.controller.dto.JwtResponseDto;
import com.ost.ecommerce.security.controller.dto.RefreshTokenDto;
import com.ost.ecommerce.security.controller.mapper.AuthMapper;
import com.ost.ecommerce.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthMapper authMapper;

    @PostMapping("/renew")
    public JwtResponseDto refreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        return authMapper.toJwtResponseDto(
                authService.refreshToken(
                    refreshTokenDto.getToken()
            )
        );
    }
}
