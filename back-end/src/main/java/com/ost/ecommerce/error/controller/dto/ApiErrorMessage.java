package com.ost.ecommerce.error.controller.dto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;

public class ApiErrorMessage {
    public final String traceId;
    public final String code;
    public final String text;
    public final Map<String, Object> args;
    private static Random _rand = null;

    public ApiErrorMessage(String code, String text) {
        this(code, text, Collections.emptyMap());
    }

    public ApiErrorMessage(String code, String text, Map<String, Object> args) {
        this.traceId = String.format("%06d", getRandom().nextInt(999999));
        this.code = code;
        this.text = text;
        this.args = args;
    }

    public ApiErrorMessage(String code, String text, Consumer<Map<String, Object>> args) {
        this.traceId = String.format("%06d", getRandom().nextInt(999999));
        this.code = code;
        this.text = text;
        Map<String, Object> map = new HashMap<>();
        args.accept(map);
        this.args = map;
    }

    private static Random getRandom() {
        if (_rand == null) {
            _rand = new Random();
        }
        return _rand;
    }
}