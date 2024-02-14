package com.ost.ecommerce.subscription.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "user_subscription")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSubscription {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "subscription_type_id")
    private ESubscriptionType subscriptionType;

    @Column(name = "subscription_state_id")
    private ESubscriptionState subscriptionState;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "auto_renewal")
    private Boolean autoRenewal;

    @Column(name = "count_monthly")
    private Integer countMonthly;

    @Column(name = "count_yearly")
    private Integer countYearly;

    public static UserSubscription fromNewUser(
            Integer userId
    ){
        UserSubscription userSubscription = new UserSubscription();
        userSubscription.setUserId(userId);
        userSubscription.setSubscriptionState(ESubscriptionState.INACTIVE);
        userSubscription.setSubscriptionType(ESubscriptionType.NO_SUBSCRIPTION);
        userSubscription.setCountMonthly(0);
        userSubscription.setCountYearly(0);
        return userSubscription;
    }

    public void activate(ESubscriptionType subscriptionType){
        this.subscriptionType = subscriptionType;
        this.subscriptionState = ESubscriptionState.ACTIVE;
        this.startDate = LocalDateTime.now();
        if (subscriptionType.equals(ESubscriptionType.MONTHLY)) {
            addMonthlyCount();
            this.expirationDate = startDate.plus(1, ChronoUnit.MONTHS);
        }
        else {
            addYearlyCount();
            this.expirationDate = startDate.plus(1, ChronoUnit.YEARS);
        }
    }

    private void addMonthlyCount(){
        this.countMonthly++;
    }

    private void addYearlyCount(){
        this.countYearly++;
    }
}
