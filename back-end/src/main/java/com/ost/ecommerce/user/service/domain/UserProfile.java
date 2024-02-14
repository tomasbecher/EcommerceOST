package com.ost.ecommerce.user.service.domain;

import com.ost.ecommerce.subscription.repository.entity.ESubscriptionState;
import com.ost.ecommerce.subscription.repository.entity.ESubscriptionType;
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
public class UserProfile {

    private String name;
    private String lastName;
    private String email;
    private Boolean receiveNewsletter;
    private ESubscriptionType userSubscriptionType;
    private ESubscriptionState subscriptionState;
    private LocalDateTime startDate;
    private LocalDateTime expirationDate;
    Set<ESubscriptionType> availableSubscriptionTypeList;
    private String onlySubscribersContent;

}
