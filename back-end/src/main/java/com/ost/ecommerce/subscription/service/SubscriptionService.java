package com.ost.ecommerce.subscription.service;

import com.ost.ecommerce.subscription.repository.entity.UserSubscription;
import com.ost.ecommerce.subscription.service.domain.SubscriptionSucceeded;

public interface SubscriptionService {

    UserSubscription create(Integer userId);
    Integer succeeded(SubscriptionSucceeded subscriptionSucceeded);
    UserSubscription getByUserIdOrFail(Integer userId);
}
