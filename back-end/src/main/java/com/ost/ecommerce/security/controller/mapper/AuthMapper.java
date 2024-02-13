package com.ost.ecommerce.security.controller.mapper;

import com.ost.ecommerce.security.controller.dto.JwtResponseDto;
import com.ost.ecommerce.security.service.domain.JwtResponse;
import org.mapstruct.Mapper;

@Mapper
public interface AuthMapper {

    JwtResponseDto toJwtResponseDto(JwtResponse jwtResponse);
}
