package com.unco.reanimators.gui.reanimators.theme.reanimators;

import com.unco.reanimators.gui.reanimators.RenderHelper;
import com.unco.reanimators.gui.reanimators.component.SettingsPanel;
import com.unco.reanimators.gui.rgui.render.AbstractComponentUI;
import com.unco.reanimators.gui.rgui.render.font.FontRenderer;

import static org.lwjgl.opengl.GL11.*;

public class ReanimatorsSettingsPanelUI extends AbstractComponentUI<SettingsPanel> {

    @Override
    public void renderComponent(SettingsPanel component, FontRenderer fontRenderer) {
        super.renderComponent(component, fontRenderer);

        glLineWidth(2f);
        glColor4f(1.0f, 1.0f, 1.0f, 0.3f);
        RenderHelper.drawFilledRectangle(0, 0, component.getWidth(), component.getHeight());
        glColor4f(1f, 1f, 1f, 1.0f);
        glLineWidth(1.5f);
        RenderHelper.drawRectangle(0, 0, component.getWidth(), component.getHeight());
    }
}
