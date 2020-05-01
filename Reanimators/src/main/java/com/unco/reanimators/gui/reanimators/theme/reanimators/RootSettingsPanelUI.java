package com.unco.reanimators.gui.reanimators.theme.reanimators;

import com.unco.reanimators.gui.reanimators.RenderHelper;
import com.unco.reanimators.gui.reanimators.component.SettingsPanel;
import com.unco.reanimators.gui.rgui.render.AbstractComponentUI;
import com.unco.reanimators.gui.rgui.render.font.FontRenderer;

import static org.lwjgl.opengl.GL11.glColor4f;

public class RootSettingsPanelUI extends AbstractComponentUI<SettingsPanel> {

    @Override
    public void renderComponent(SettingsPanel component, FontRenderer fontRenderer) {
        glColor4f(1,0.33f,0.33f,0.2f);
        RenderHelper.drawOutlinedRoundedRectangle(0,0,component.getWidth(),component.getHeight(), 6f, 0.14f,0.14f,0.14f,component.getOpacity(),1f);
    }

}
