package com.unco.reanimators.gui.reanimators.theme.reanimators;

import com.unco.reanimators.Reanimators;
import com.unco.reanimators.gui.reanimators.*;
import com.unco.reanimators.gui.rgui.GUI;
import com.unco.reanimators.gui.rgui.component.AlignedComponent;
import com.unco.reanimators.gui.rgui.component.Component;
import com.unco.reanimators.gui.rgui.component.container.Container;
import com.unco.reanimators.gui.rgui.component.container.use.Frame;
import com.unco.reanimators.gui.rgui.component.listen.MouseListener;
import com.unco.reanimators.gui.rgui.component.listen.UpdateListener;
import com.unco.reanimators.gui.rgui.poof.use.FramePoof;
import com.unco.reanimators.gui.rgui.render.AbstractComponentUI;
import com.unco.reanimators.gui.rgui.render.font.FontRenderer;
import com.unco.reanimators.gui.rgui.util.ContainerHelper;
import com.unco.reanimators.gui.rgui.util.Docking;
import com.unco.reanimators.util.Bind;
import com.unco.reanimators.util.ColourHolder;
import com.unco.reanimators.util.Wrapper;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class ReanimatorsFrameUI<T extends Frame> extends AbstractComponentUI<Frame> {

    ColourHolder frameColour = ReanimatorsGUI.primaryColour.setA(100);
    ColourHolder outlineColour = frameColour.darker();

    Component yLineComponent = null;
    Component xLineComponent = null;
    Component centerXComponent = null;
    Component centerYComponent = null;
    boolean centerX = false;
    boolean centerY = false;
    int xLineOffset = 0;

    private static final RootFontRenderer ff = new RootSmallFontRenderer();

    @Override
    public void renderComponent(Frame component, FontRenderer fontRenderer) {
        if (component.getOpacity() == 0)
            return;
        glDisable(GL_TEXTURE_2D);

        glColor4f(1f, 1f, 1f, 0.3f);
        RenderHelper.drawFilledRectangle(0, 0, component.getWidth(), component.getHeight());
        
        glColor4f(1f, 1f, 1f, 0.3f);
        glLineWidth(1.5f);
        RenderHelper.drawRectangle(0, 0, component.getWidth(), component.getHeight());

        glColor4f(1f, 1f, 1f, 1.0f);
        glLineWidth(1.5f);
        RenderHelper.drawRectangle(0, 0, component.getWidth(), ff.getStringHeight(component.getTitle()) + 6);

        GL11.glColor3f(1,1,1);
        ff.drawString(component.getWidth() / 2 - ff.getStringWidth(component.getTitle()) / 2, 1, component.getTitle());

        int top_y = 5;
        int bottom_y = component.getTheme().getFontRenderer().getFontHeight() - 9;

        if (component.isPinneable()){
            if (component.isPinned())
                glColor3f(0f, 0f, 1f);
            else
                glColor3f(0.66f,0.66f,0.66f);
            RenderHelper.drawCircle(7,4,2f);
            glLineWidth(3f);
            glBegin(GL_LINES);
            {
                glVertex2d(7,4);
                glVertex2d(4,8);
            }
            glEnd();
        }

        if (component.equals(xLineComponent)){
            glColor3f(.44f,.44f,.44f);
            glLineWidth(1f);
            glBegin(GL_LINES);
            {
                glVertex2d(xLineOffset,-GUI.calculateRealPosition(component)[1]);
                glVertex2d(xLineOffset, Wrapper.getMinecraft().displayHeight);
            }
            glEnd();
        }

        if (component == centerXComponent && centerX) {
            glColor3f(0.0f, 0.03f, 1f);
            glLineWidth(1f);
            glBegin(GL_LINES);
            {
                double x = component.getWidth() / 2;
                glVertex2d(x, -GUI.calculateRealPosition(component)[1]);
                glVertex2d(x, Wrapper.getMinecraft().displayHeight);
            }
            glEnd();
        }

        if (component.equals(yLineComponent)){
            glColor3f(.44f,.44f,.44f);
            glLineWidth(1f);
            glBegin(GL_LINES);
            {
                glVertex2d(-GUI.calculateRealPosition(component)[0],0);
                glVertex2d(Wrapper.getMinecraft().displayWidth, 0);
            }
            glEnd();
        }

        if (component == centerYComponent && centerY) {
            glColor3f(0.0f, 0.03f, 1f);
            glLineWidth(1f);
            glBegin(GL_LINES);
            {
                double y = component.getHeight() / 2;
                glVertex2d(-GUI.calculateRealPosition(component)[0], y);
                glVertex2d(Wrapper.getMinecraft().displayWidth, y);
            }
            glEnd();
        }

        glDisable(GL_BLEND);
    }

    @Override
    public void handleMouseRelease(Frame component, int x, int y, int button) {
        yLineComponent = null;
        xLineComponent = null;
        centerXComponent = null;
        centerYComponent = null;
    }

    @Override
    public void handleMouseDrag(Frame component, int x, int y, int button) {
        super.handleMouseDrag(component, x, y, button);
    }

    @Override
    public void handleAddComponent(Frame component, Container container) {
        super.handleAddComponent(component, container);
        component.setOriginOffsetY(component.getTheme().getFontRenderer().getFontHeight() + 3);
        component.setOriginOffsetX(3);

        component.addMouseListener(new MouseListener() {
            @Override
            public void onMouseDown(MouseButtonEvent event) {
                int y = event.getY();
                int x = event.getX();
                if (y < 0){
                    if (x > component.getWidth() - 22){
                        if (component.isMinimizeable() && component.isCloseable()){
                            if (y > -component.getOriginOffsetY()/2){
                                if (component.isMinimized()){
                                    component.callPoof(FramePoof.class, new FramePoof.FramePoofInfo(FramePoof.Action.MAXIMIZE));
                                }else {
                                    component.callPoof(FramePoof.class, new FramePoof.FramePoofInfo(FramePoof.Action.MINIMIZE));
                                }
                            }else{
                                component.callPoof(FramePoof.class, new FramePoof.FramePoofInfo(FramePoof.Action.CLOSE));
                            }
                        }else{
                            if (component.isMinimized() && component.isMinimizeable()){
                                component.callPoof(FramePoof.class, new FramePoof.FramePoofInfo(FramePoof.Action.MAXIMIZE));
                            }else if (component.isMinimizeable()){
                                component.callPoof(FramePoof.class, new FramePoof.FramePoofInfo(FramePoof.Action.MINIMIZE));
                            }else if (component.isCloseable()) {
                                component.callPoof(FramePoof.class, new FramePoof.FramePoofInfo(FramePoof.Action.CLOSE));
                            }
                        }
                    }
                    if (x < 10 && x > 0){
                        if (component.isPinneable()){
                            component.setPinned(!component.isPinned());
                        }
                    }
                }
            }

            @Override
            public void onMouseRelease(MouseButtonEvent event) {

            }

            @Override
            public void onMouseDrag(MouseButtonEvent event) {

            }

            @Override
            public void onMouseMove(MouseMoveEvent event) {
            }

            @Override
            public void onScroll(MouseScrollEvent event) {

            }
        });

        component.addUpdateListener(new UpdateListener() {
            @Override
            public void updateSize(Component component, int oldWidth, int oldHeight) {
                if (component instanceof Frame) {
                    ReanimatorsGUI.dock((Frame) component);
                }
            }
            @Override
            public void updateLocation(Component component, int oldX, int oldY) { }
        });

        component.addPoof(new Frame.FrameDragPoof<Frame, Frame.FrameDragPoof.DragInfo>() {
            @Override
            public void execute(Frame component, DragInfo info) {
                if (Bind.isShiftDown() || Bind.isAltDown() || Bind.isCtrlDown()) return;
                int x = info.getX();
                int y = info.getY();
                yLineComponent = null;
                xLineComponent = null;

                component.setDocking(Docking.NONE);

                ReanimatorsGUI rootGUI = Reanimators.getInstance().getGuiManager();
                for (Component c : rootGUI.getChildren()){
                    if (c.equals(component)) continue;

                    int yDiff = Math.abs(y - c.getY());
                    if (yDiff < 4){
                        y = c.getY();
                        yLineComponent = component;
                    }

                    yDiff = Math.abs(y - (c.getY() + c.getHeight() + 3));
                    if (yDiff < 4) {
                        y = c.getY() + c.getHeight();
                        y += 3;
                        yLineComponent = component;
                    }

                    int xDiff = Math.abs((x + component.getWidth()) - (c.getX() + c.getWidth()));
                    if (xDiff < 4){
                        x = c.getX() + c.getWidth();
                        x -= component.getWidth();
                        xLineComponent = component;
                        xLineOffset = component.getWidth();
                    }

                    xDiff = Math.abs(x - c.getX());
                    if (xDiff < 4){
                        x = c.getX();
                        xLineComponent = component;
                        xLineOffset = 0;
                    }

                    xDiff = Math.abs(x - (c.getX() + c.getWidth() + 3));
                    if (xDiff < 4){
                        x = c.getX() + c.getWidth() + 3;
                        xLineComponent = component;
                        xLineOffset = 0;
                    }

                }

                if (x < 5) {
                    x = 0;
                    ContainerHelper.setAlignment(component, AlignedComponent.Alignment.LEFT);
                    component.setDocking(Docking.LEFT);
                }
                int diff = (x+component.getWidth()) * DisplayGuiScreen.getScale() - Wrapper.getMinecraft().displayWidth;
                if (-diff < 5){
                    x = (Wrapper.getMinecraft().displayWidth / DisplayGuiScreen.getScale())-component.getWidth();
                    ContainerHelper.setAlignment(component, AlignedComponent.Alignment.RIGHT);
                    component.setDocking(Docking.RIGHT);
                }

                if (y < 5) {
                    y = 0;
                    if (component.getDocking().equals(Docking.RIGHT))
                        component.setDocking(Docking.TOPRIGHT);
                    else if (component.getDocking().equals(Docking.LEFT))
                        component.setDocking(Docking.TOPLEFT);
                    else
                        component.setDocking(Docking.TOP);
                }

                diff = (y+component.getHeight()) * DisplayGuiScreen.getScale() - Wrapper.getMinecraft().displayHeight;
                if (-diff < 5) {
                    y = (Wrapper.getMinecraft().displayHeight / DisplayGuiScreen.getScale()) - component.getHeight();

                    if (component.getDocking().equals(Docking.RIGHT))
                        component.setDocking(Docking.BOTTOMRIGHT);
                    else if (component.getDocking().equals(Docking.LEFT))
                        component.setDocking(Docking.BOTTOMLEFT);
                    else
                        component.setDocking(Docking.BOTTOM);
                }

                if (Math.abs(((x + component.getWidth() / 2) * DisplayGuiScreen.getScale() * 2) - Wrapper.getMinecraft().displayWidth) < 5) { // Component is center-aligned on the x axis
                    xLineComponent = null;
                    centerXComponent = component;
                    centerX = true;
                    x = (Wrapper.getMinecraft().displayWidth / (DisplayGuiScreen.getScale() * 2)) - component.getWidth() / 2;
                    if (component.getDocking().isTop()) {
                        component.setDocking(Docking.CENTERTOP);
                    } else if (component.getDocking().isBottom()){
                        component.setDocking(Docking.CENTERBOTTOM);
                    } else {
                        component.setDocking(Docking.CENTERVERTICAL);
                    }
                    ContainerHelper.setAlignment(component, AlignedComponent.Alignment.CENTER);
                } else {
                    centerX = false;
                }

                if (Math.abs(((y + component.getHeight() / 2) * DisplayGuiScreen.getScale() * 2) - Wrapper.getMinecraft().displayHeight) < 5) { // Component is center-aligned on the y axis
                    yLineComponent = null;
                    centerYComponent = component;
                    centerY = true;
                    y = (Wrapper.getMinecraft().displayHeight / (DisplayGuiScreen.getScale() * 2)) - component.getHeight() / 2;
                    if (component.getDocking().isLeft()) {
                        component.setDocking(Docking.CENTERLEFT);
                    } else if (component.getDocking().isRight()) {
                        component.setDocking(Docking.CENTERRIGHT);
                    } else if (component.getDocking().isCenterHorizontal()) {
                        component.setDocking(Docking.CENTER);
                    } else {
                        component.setDocking(Docking.CENTERHOIZONTAL);
                    }
                } else {
                    centerY = false;
                }

                info.setX(x);
                info.setY(y);
            }
        });
    }
}