package com.ost.ecommerce.subscription.controller.dto;

import com.ost.ecommerce.subscription.repository.entity.ESubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SubscriptionSucceededDto {

    ESubscriptionType subscriptionType;
}
