package com.ost.ecommerce.subscription.controller;

import com.ost.ecommerce.subscription.controller.dto.SubscriptionSucceededDto;
import com.ost.ecommerce.subscription.controller.mapper.SubscriptionMapper;
import com.ost.ecommerce.subscription.service.SubscriptionService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper;

    @PostMapping("/succeeded")
    public ResponseEntity<Integer> succeeded(@RequestBody SubscriptionSucceededDto subscriptionSucceededDto) throws StripeException {
        return new ResponseEntity<Integer>(
                subscriptionService.succeeded(
                        subscriptionMapper.fromSubscriptionSucceededDto(subscriptionSucceededDto)
                ),
                HttpStatus.OK
        );
    }
}
