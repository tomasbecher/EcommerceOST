package com.ost.ecommerce.subscription.repository.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ESubscriptionType {

    NO_SUBSCRIPTION(0,"No subscription",0),
    MONTHLY(1,"Monthly",9.99),
    YEARLY(2,"Yearly",90);

    private final Short id;
    private final String description;
    private final Double price;

    ESubscriptionType(Number id, String description, double price){
        this.id = id.shortValue();
        this.description = description;
        this.price = price;
    }

    public static Short toDBValue(ESubscriptionType subscription) {
        return Arrays
                .stream(ESubscriptionType.values())
                .filter(subscriptionType -> subscriptionType.equals(subscription))
                .findFirst()
                .map(ESubscriptionType::getId)
                .orElse(null);
    }

    public static ESubscriptionType fromDBValue(Short dbData) {
        return Arrays
                .stream(ESubscriptionType.values())
                .filter(subscription -> subscription.getId().equals(dbData))
                .findFirst()
                .orElse(null);
    }

}
