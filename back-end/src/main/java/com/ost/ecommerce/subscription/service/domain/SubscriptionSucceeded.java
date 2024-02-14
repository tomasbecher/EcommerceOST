package com.ost.ecommerce.subscription.service.domain;

import com.ost.ecommerce.subscription.repository.entity.ESubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SubscriptionSucceeded {

    private ESubscriptionType subscriptionType;

}
