package com.ost.ecommerce.stripe.service;

import com.ost.ecommerce.stripe.service.domain.PaymentIntentCreate;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface PaymentService {

    String charge(PaymentIntentCreate paymentIntentCreate) throws StripeException;
    PaymentIntent paymentIntent(PaymentIntentCreate paymentIntentCreate) throws StripeException;
    PaymentIntent confirm(String id) throws StripeException;
    PaymentIntent cancel(String id) throws StripeException;
}
