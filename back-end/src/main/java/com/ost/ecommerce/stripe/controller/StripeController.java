package com.ost.ecommerce.stripe.controller;

import com.ost.ecommerce.stripe.controller.dto.PaymentIntentCreateDto;
import com.ost.ecommerce.stripe.controller.mapper.PaymentMapper;
import com.ost.ecommerce.stripe.service.PaymentService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping("/stripe")
@RequiredArgsConstructor
public class StripeController {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @PostMapping("/payment")
    public ResponseEntity<String> payment(@RequestBody PaymentIntentCreateDto paymentIntentCreateDto) throws StripeException {
        String chargeId = paymentService.charge(
                paymentMapper.fromPaymentIntentCreateDto(paymentIntentCreateDto)
        );
        return chargeId != null ?
                new ResponseEntity<String>(chargeId,HttpStatus.OK) :
                new ResponseEntity<String>("Please check the credit card details entered.", HttpStatus.BAD_REQUEST);
    }

}
