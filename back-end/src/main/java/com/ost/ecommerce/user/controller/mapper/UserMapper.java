package com.ost.ecommerce.user.controller.mapper;

import com.ost.ecommerce.subscription.controller.mapper.SubscriptionMapper;
import com.ost.ecommerce.user.controller.dto.UserCreateDto;
import com.ost.ecommerce.user.controller.dto.UserProfileDto;
import com.ost.ecommerce.user.service.domain.UserCreate;
import com.ost.ecommerce.user.service.domain.UserProfile;
import org.mapstruct.Mapper;

@Mapper(uses = {SubscriptionMapper.class})
public interface UserMapper {

    UserCreate fromUserCreateDto(UserCreateDto userCreateDto);
    UserProfileDto toUserProfileDto(UserProfile userProfile);
}
