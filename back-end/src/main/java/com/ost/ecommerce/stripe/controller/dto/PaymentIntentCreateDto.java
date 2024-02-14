package com.ost.ecommerce.stripe.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentIntentCreateDto {

    public enum Currency{
        USD
    }
    private int amount;
    private String token;


}
