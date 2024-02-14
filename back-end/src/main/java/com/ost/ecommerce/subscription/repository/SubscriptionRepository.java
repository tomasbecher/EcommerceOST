package com.ost.ecommerce.subscription.repository;

import com.ost.ecommerce.subscription.repository.entity.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<UserSubscription, Integer> {

    Optional<UserSubscription> findByUserId(Integer userId);
}
