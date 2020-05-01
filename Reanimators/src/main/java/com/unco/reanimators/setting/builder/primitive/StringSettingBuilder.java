package com.unco.reanimators.setting.builder.primitive;

import com.unco.reanimators.setting.builder.SettingBuilder;
import com.unco.reanimators.setting.impl.StringSetting;

public class StringSettingBuilder extends SettingBuilder<String> {
    @Override
    public StringSetting build() {
        return new StringSetting(initialValue, predicate(), consumer(), name, visibilityPredicate());
    }
}
