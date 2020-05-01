package com.unco.reanimators.gui.rgui.render.theme;

import com.unco.reanimators.gui.rgui.component.Component;
import com.unco.reanimators.gui.rgui.render.ComponentUI;
import com.unco.reanimators.gui.rgui.render.font.FontRenderer;

public interface Theme {
    public ComponentUI getUIForComponent(Component component);
    public FontRenderer getFontRenderer();
}
