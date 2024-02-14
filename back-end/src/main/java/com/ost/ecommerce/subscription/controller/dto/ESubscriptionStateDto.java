package com.ost.ecommerce.subscription.controller.dto;

import com.ost.ecommerce.subscription.repository.entity.ESubscriptionState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ESubscriptionStateDto {

    private ESubscriptionState subscriptionState;
    private String description;
}
