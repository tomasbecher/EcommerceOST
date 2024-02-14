package com.ost.ecommerce.user.controller.dto;

import com.ost.ecommerce.subscription.controller.dto.ESubscriptionStateDto;
import com.ost.ecommerce.subscription.controller.dto.ESubscriptionTypeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserProfileDto {


    private String name;
    private String lastName;
    private String email;
    private Boolean receiveNewsletter;
    private ESubscriptionTypeDto userSubscriptionType;
    private ESubscriptionStateDto subscriptionState;
    private LocalDateTime startDate;
    private LocalDateTime expirationDate;
    Set<ESubscriptionTypeDto> availableSubscriptionTypeList;
    private String onlySubscribersContent;
}
