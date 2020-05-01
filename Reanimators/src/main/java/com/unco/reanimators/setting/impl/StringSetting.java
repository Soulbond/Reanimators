package com.unco.reanimators.setting.impl;

import com.unco.reanimators.setting.Setting;
import com.unco.reanimators.setting.converter.StringConverter;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class StringSetting extends Setting<String> {

    private static final StringConverter converter = new StringConverter();

    public StringSetting(String value, Predicate<String> restriction, BiConsumer<String, String> consumer, String name, Predicate<String> visibilityPredicate) {
        super(value, restriction, consumer, name, visibilityPredicate);
    }

    @Override
    public StringConverter converter() {
        return converter;
    }

}
