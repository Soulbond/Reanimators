package com.unco.reanimators.setting.impl.numerical;

import com.unco.reanimators.setting.converter.AbstractBoxedNumberConverter;
import com.unco.reanimators.setting.converter.BoxedIntegerConverter;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class IntegerSetting extends NumberSetting<Integer> {

    private static final BoxedIntegerConverter converter = new BoxedIntegerConverter();

    public IntegerSetting(Integer value, Predicate<Integer> restriction, BiConsumer<Integer, Integer> consumer, String name, Predicate<Integer> visibilityPredicate, Integer min, Integer max) {
        super(value, restriction, consumer, name, visibilityPredicate, min, max);
    }

    @Override
    public AbstractBoxedNumberConverter converter() {
        return converter;
    }

}
