package com.unco.reanimators.setting.impl;

import com.unco.reanimators.setting.Setting;
import com.unco.reanimators.setting.converter.BooleanConverter;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class BooleanSetting extends Setting<Boolean> {

    private static final BooleanConverter converter = new BooleanConverter();

    public BooleanSetting(Boolean value, Predicate<Boolean> restriction, BiConsumer<Boolean, Boolean> consumer, String name, Predicate<Boolean> visibilityPredicate) {
        super(value, restriction, consumer, name, visibilityPredicate);
    }

    @Override
    public BooleanConverter converter() {
        return converter;
    }

}
