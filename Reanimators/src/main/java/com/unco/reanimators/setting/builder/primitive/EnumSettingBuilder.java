package com.unco.reanimators.setting.builder.primitive;

import com.unco.reanimators.setting.Setting;
import com.unco.reanimators.setting.builder.SettingBuilder;
import com.unco.reanimators.setting.impl.EnumSetting;

public class EnumSettingBuilder<T extends Enum> extends SettingBuilder<T> {
    Class<? extends Enum> clazz;

    public EnumSettingBuilder(Class<? extends Enum> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Setting<T> build() {
        return new EnumSetting<>(initialValue, predicate(), consumer(), name, visibilityPredicate(), clazz);
    }
}
