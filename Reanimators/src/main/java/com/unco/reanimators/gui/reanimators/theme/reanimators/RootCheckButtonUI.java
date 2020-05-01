package com.unco.reanimators.gui.reanimators.theme.reanimators;

import com.unco.reanimators.gui.reanimators.ReanimatorsGUI;
import com.unco.reanimators.gui.rgui.component.container.Container;
import com.unco.reanimators.gui.rgui.component.use.CheckButton;
import com.unco.reanimators.gui.rgui.render.AbstractComponentUI;
import com.unco.reanimators.gui.rgui.render.font.FontRenderer;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class RootCheckButtonUI<T extends CheckButton> extends AbstractComponentUI<CheckButton> {

    protected Color backgroundColour = new Color(0, 0, 255);
    protected Color backgroundColourHover = new Color(0, 0, 255);

    protected Color idleColourNormal = new Color(0, 0, 255);
    protected Color downColourNormal = new Color(0, 0, 255);

    protected Color idleColourToggle = new Color(0, 0, 255);
    protected Color downColourToggle = idleColourToggle.brighter();

    @Override
    public void renderComponent(CheckButton component, FontRenderer ff) {

        glColor4f(backgroundColour.getRed()/255f, backgroundColour.getGreen()/255f, backgroundColour.getBlue()/255f, component.getOpacity());
        if (component.isToggled()){
            glColor3f(.9f, backgroundColour.getGreen()/255f, backgroundColour.getBlue()/255f);
        }
        if (component.isHovered() || component.isPressed()){
            glColor4f(backgroundColourHover.getRed()/255f, backgroundColourHover.getGreen()/255f, backgroundColourHover.getBlue()/255f, component.getOpacity());
        }

        String text = component.getName();
        int c = component.isPressed() ? 0xaaaaaa : component.isToggled() ? 0x0000ff : 0xdddddd;
        if (component.isHovered())
            c = (c & 0x0096ff) << 1;

        glColor3f(1,1,1);
        glEnable(GL_TEXTURE_2D);
        ReanimatorsGUI.fontRenderer.drawString(1, ReanimatorsGUI.fontRenderer.getFontHeight()/2-1, c, text);
        glDisable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);
    }

    @Override
    public void handleAddComponent(CheckButton component, Container container) {
        component.setWidth(ReanimatorsGUI.fontRenderer.getStringWidth(component.getName()) + 28);
        component.setHeight(ReanimatorsGUI.fontRenderer.getFontHeight()+2);
    }
}
