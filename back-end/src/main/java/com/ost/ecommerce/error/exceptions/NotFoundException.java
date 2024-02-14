package com.ost.ecommerce.error.exceptions;

import org.slf4j.helpers.MessageFormatter;

import java.util.function.Supplier;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -5865520379695280332L;
    public final String messageId;

    public NotFoundException(String messageId, String message) {
        super(message);
        this.messageId = messageId;
    }

    public NotFoundException(String messageId, String message, Object ... params) {
        super(MessageFormatter.arrayFormat(message, params).getMessage());
        this.messageId = messageId;
    }

    public static final Supplier<NotFoundException> supplier(String messageId, String message) {
        return () -> new NotFoundException(messageId, message);
    }

    public static final Supplier<NotFoundException> supplier(String messageId, String message, Object ... params) {
        return () -> new NotFoundException(messageId, message, params);
    }

}
