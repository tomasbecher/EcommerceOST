package com.ost.ecommerce.subscription.repository.entity.converter;

import com.ost.ecommerce.subscription.repository.entity.ESubscriptionState;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ESubscriptionStateConverter implements AttributeConverter<ESubscriptionState, Short> {

    @Override
    public Short convertToDatabaseColumn(ESubscriptionState subscriptionState) {
        return ESubscriptionState.toDBValue(subscriptionState);
    }

    @Override
    public ESubscriptionState convertToEntityAttribute(Short aShort) {
        return ESubscriptionState.fromDBValue(aShort);
    }
}
