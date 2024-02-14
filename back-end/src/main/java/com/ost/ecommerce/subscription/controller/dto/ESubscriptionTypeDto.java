package com.ost.ecommerce.subscription.controller.dto;

import com.ost.ecommerce.subscription.repository.entity.ESubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ESubscriptionTypeDto {

    private ESubscriptionType subscriptionType;
    private String description;
    private Double price;
}
