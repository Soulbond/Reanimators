package com.unco.reanimators.setting;

public interface ISettingUnknown {
    String getName();

    Class getValueClass();

    String getValueAsString();

    boolean isVisible();

    void setValueFromString(String value);
}
