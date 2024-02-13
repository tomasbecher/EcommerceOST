package com.ost.ecommerce.user.controller.mapper;

import com.ost.ecommerce.user.controller.dto.UserCreateDto;
import com.ost.ecommerce.user.service.domain.UserCreate;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserCreate fromUserCreateDto(UserCreateDto userCreateDto);
}
