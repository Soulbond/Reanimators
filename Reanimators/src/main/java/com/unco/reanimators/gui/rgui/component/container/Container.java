package com.unco.reanimators.gui.rgui.component.container;

import com.unco.reanimators.gui.rgui.component.Component;

import java.util.ArrayList;

public interface Container extends Component {
    public ArrayList<Component> getChildren();
    public Component getComponentAt(int x, int y);
    public Container addChild(Component... component);
    public Container removeChild(Component component);
    public boolean hasChild(Component component);
    public void renderChildren();

    public int getOriginOffsetX();
    public int getOriginOffsetY();

    public boolean penetrateTest(int x, int y);
}
