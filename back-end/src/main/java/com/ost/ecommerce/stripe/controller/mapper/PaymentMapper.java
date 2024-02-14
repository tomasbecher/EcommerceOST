package com.ost.ecommerce.stripe.controller.mapper;

import com.ost.ecommerce.stripe.controller.dto.PaymentIntentCreateDto;
import com.ost.ecommerce.stripe.service.domain.PaymentIntentCreate;
import org.mapstruct.Mapper;

@Mapper
public interface PaymentMapper {

    PaymentIntentCreate fromPaymentIntentCreateDto(PaymentIntentCreateDto paymentIntentCreateDto);
}
