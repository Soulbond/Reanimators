package com.unco.reanimators.setting.builder.primitive;

import com.unco.reanimators.setting.impl.BooleanSetting;
import com.unco.reanimators.setting.builder.SettingBuilder;

public class BooleanSettingBuilder extends SettingBuilder<Boolean> {
    @Override
    public BooleanSetting build() {
        return new BooleanSetting(initialValue, predicate(), consumer(), name, visibilityPredicate());
    }

    @Override
    public BooleanSettingBuilder withName(String name) {
        return (BooleanSettingBuilder) super.withName(name);
    }
}
