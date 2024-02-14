package com.ost.ecommerce.subscription.repository.entity.converter;

import com.ost.ecommerce.subscription.repository.entity.ESubscriptionType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ESubscriptionTypeConverter implements AttributeConverter<ESubscriptionType, Short> {

    @Override
    public Short convertToDatabaseColumn(ESubscriptionType subscriptionType) {
        return ESubscriptionType.toDBValue(subscriptionType);
    }

    @Override
    public ESubscriptionType convertToEntityAttribute(Short aShort) {
        return ESubscriptionType.fromDBValue(aShort);
    }
}
