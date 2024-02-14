package com.ost.ecommerce.subscription.service.impl;

import com.ost.ecommerce.permissions.service.RoleService;
import com.ost.ecommerce.subscription.repository.SubscriptionRepository;
import com.ost.ecommerce.subscription.repository.entity.ESubscriptionState;
import com.ost.ecommerce.subscription.repository.entity.UserSubscription;
import com.ost.ecommerce.subscription.service.SubscriptionService;
import com.ost.ecommerce.subscription.service.domain.SubscriptionSucceeded;
import com.ost.ecommerce.user.repository.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final RoleService roleService;

    @Override
    @Transactional
    public UserSubscription create(Integer userId) {
        return subscriptionRepository.save(
                UserSubscription.fromNewUser(userId)
        );
    }

    @Override
    @Transactional
    public Integer succeeded(SubscriptionSucceeded subscriptionSucceeded) {
        // TODO agregar a ExceptionHandler
        User user = roleService.getCurrentUser();
        UserSubscription userSubscription = getByUserIdOrFail(user.getId());
        if (userSubscription.getSubscriptionState().equals(ESubscriptionState.ACTIVE))
            throw new RuntimeException("User is already subscribed.");
        userSubscription.activate(subscriptionSucceeded.getSubscriptionType());
        return subscriptionRepository.save(userSubscription).getId();
    }

    @Override
    public UserSubscription getByUserIdOrFail(Integer userId) {
        // TODO agregar a ExceptionHandler
        return subscriptionRepository.findByUserId(userId).orElseThrow(
                () -> new RuntimeException(
                        "Subscription not found.")
        );
    }

}
