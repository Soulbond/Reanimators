package com.unco.reanimators.gui.rgui.poof;

import com.unco.reanimators.gui.rgui.component.Component;

public interface IPoof<T extends Component, S extends PoofInfo> {
    public void execute(T component, S info);
    public Class getComponentClass();
    public Class getInfoClass();
}
