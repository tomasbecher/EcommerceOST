package com.ost.ecommerce.subscription.controller.mapper;

import com.ost.ecommerce.subscription.controller.dto.ESubscriptionStateDto;
import com.ost.ecommerce.subscription.controller.dto.ESubscriptionTypeDto;
import com.ost.ecommerce.subscription.controller.dto.SubscriptionSucceededDto;
import com.ost.ecommerce.subscription.repository.entity.ESubscriptionState;
import com.ost.ecommerce.subscription.repository.entity.ESubscriptionType;
import com.ost.ecommerce.subscription.service.domain.SubscriptionSucceeded;
import org.mapstruct.Mapper;

@Mapper
public interface SubscriptionMapper {


    SubscriptionSucceeded fromSubscriptionSucceededDto(SubscriptionSucceededDto subscriptionSucceededDto);

    static ESubscriptionTypeDto map(ESubscriptionType subscriptionType) {
        return new ESubscriptionTypeDto(
                subscriptionType,
                subscriptionType != null ? subscriptionType.getDescription() : null,
                subscriptionType != null ? subscriptionType.getPrice() : null
        );
    }

    static ESubscriptionStateDto map(ESubscriptionState subscriptionState) {
        return new ESubscriptionStateDto(
                subscriptionState,
                subscriptionState != null ? subscriptionState.getDescription() : null
        );
    }
}
