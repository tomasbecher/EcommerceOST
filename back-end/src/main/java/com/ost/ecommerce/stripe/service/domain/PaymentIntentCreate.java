package com.ost.ecommerce.stripe.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentIntentCreate {

    public enum Currency{
        USD
    }

    private int amount;
    private String token;
}
