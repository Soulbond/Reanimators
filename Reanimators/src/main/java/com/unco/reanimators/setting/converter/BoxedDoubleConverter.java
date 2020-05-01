package com.unco.reanimators.setting.converter;

import com.google.gson.JsonElement;

public class BoxedDoubleConverter extends AbstractBoxedNumberConverter<Double> {
    @Override
    protected Double doBackward(JsonElement s) {
        return s.getAsDouble();
    }
}
