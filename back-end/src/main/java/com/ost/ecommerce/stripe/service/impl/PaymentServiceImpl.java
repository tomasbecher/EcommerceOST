package com.ost.ecommerce.stripe.service.impl;

import com.ost.ecommerce.stripe.service.PaymentService;
import com.ost.ecommerce.stripe.service.domain.PaymentIntentCreate;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${stripe.key.secret}")
    String secretKey;

    @Override
    public String charge(PaymentIntentCreate paymentIntentCreate) throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", paymentIntentCreate.getAmount());
        chargeParams.put("currency", PaymentIntentCreate.Currency.USD);
        chargeParams.put("source", paymentIntentCreate.getToken());

        Charge charge = Charge.create(chargeParams);
        return charge.getId();
    }

    @Override
    public PaymentIntent paymentIntent(PaymentIntentCreate paymentIntentCreate) throws StripeException {
        Stripe.apiKey = secretKey;
        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");
        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentIntentCreate.getAmount());
        params.put("payment_method_types", paymentMethodTypes);
        return PaymentIntent.create(params);
    }

    @Override
    public PaymentIntent confirm(String id) throws StripeException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
        Map<String, Object> params = new HashMap<>();
        params.put("payment_method", "pm_card_visa");
        paymentIntent.confirm(params);
        return paymentIntent;
    }

    @Override
    public PaymentIntent cancel(String id) throws StripeException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
        paymentIntent.cancel();
        return paymentIntent;
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }
}
