package com.ost.ecommerce.subscription.repository.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ESubscriptionState {

    INACTIVE(0,"Inactive"),
    ACTIVE(1,"Active"),
    EXPIRED(2,"Expired"),
    CANCELLED(2,"Cancelled");

    private final Short id;
    private final String description;

    ESubscriptionState(Number id, String description){
        this.id = id.shortValue();
        this.description = description;
    }

    public static Short toDBValue(ESubscriptionState subscription) {
        return Arrays
                .stream(ESubscriptionState.values())
                .filter(subscriptionState -> subscriptionState.equals(subscription))
                .findFirst()
                .map(ESubscriptionState::getId)
                .orElse(null);
    }

    public static ESubscriptionState fromDBValue(Short dbData) {
        return Arrays
                .stream(ESubscriptionState.values())
                .filter(subscription -> subscription.getId().equals(dbData))
                .findFirst()
                .orElse(null);
    }
}
