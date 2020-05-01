package com.unco.reanimators.gui.reanimators.theme.reanimators;

import com.unco.reanimators.gui.reanimators.ReanimatorsGUI;
import com.unco.reanimators.gui.reanimators.RenderHelper;
import com.unco.reanimators.gui.rgui.component.container.Container;
import com.unco.reanimators.gui.rgui.component.use.Button;
import com.unco.reanimators.gui.rgui.render.AbstractComponentUI;
import com.unco.reanimators.gui.rgui.render.font.FontRenderer;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class RootButtonUI<T extends Button> extends AbstractComponentUI<Button> {

    protected Color idleColour = new Color(0, 0, 255);
    protected Color downColour = new Color(0, 0, 150);

    @Override
    public void renderComponent(Button component, FontRenderer ff) {
        glColor3f(0.22f,0.22f,0.22f);
        if (component.isHovered() || component.isPressed()){
            glColor3f(0.26f,0.26f,0.26f);
        }

        RenderHelper.drawRoundedRectangle(0, 0, component.getWidth(), component.getHeight(), 3f);

        glColor3f(1,1,1);
        glEnable(GL_TEXTURE_2D);
        ReanimatorsGUI.fontRenderer.drawString(1, 0, component.isPressed() ? downColour : idleColour, component.getName());
        glDisable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);
    }

    @Override
    public void handleAddComponent(Button component, Container container) {
        component.setWidth(ReanimatorsGUI.fontRenderer.getStringWidth(component.getName()) + 28);
        component.setHeight(ReanimatorsGUI.fontRenderer.getFontHeight()+2);
    }
}
