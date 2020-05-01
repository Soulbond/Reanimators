package com.unco.reanimators.gui.reanimators.theme.reanimators;

import com.unco.reanimators.gui.reanimators.component.UnboundSlider;
import com.unco.reanimators.gui.rgui.component.container.Container;
import com.unco.reanimators.gui.rgui.render.AbstractComponentUI;
import com.unco.reanimators.gui.rgui.render.font.FontRenderer;
import org.lwjgl.opengl.GL11;

public class ReanimatorsUnboundSliderUI extends AbstractComponentUI<UnboundSlider> {
    @Override
    public void renderComponent(UnboundSlider component, FontRenderer fontRenderer) {
        String s = component.getText() + ": " + component.getValue();
        int c = component.isPressed() ? 0x0000ff : 0x0000ff;
        if (component.isHovered())
            c = (c & 0x7f7f7f) << 1;
        fontRenderer.drawString(1, component.getHeight() - fontRenderer.getFontHeight()/2-4, c, s);
        GL11.glDisable(GL11.GL_BLEND);
    }

    @Override
    public void handleAddComponent(UnboundSlider component, Container container) {
        component.setHeight(component.getTheme().getFontRenderer().getFontHeight());
        component.setWidth(component.getTheme().getFontRenderer().getStringWidth(component.getText()));
    }

}
