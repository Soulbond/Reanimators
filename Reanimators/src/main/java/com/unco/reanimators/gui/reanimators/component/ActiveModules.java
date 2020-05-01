package com.unco.reanimators.gui.reanimators.component;

import com.unco.reanimators.gui.rgui.component.container.use.Frame;
import com.unco.reanimators.gui.rgui.component.listen.RenderListener;
import com.unco.reanimators.gui.rgui.component.use.Label;
import com.unco.reanimators.gui.rgui.util.ContainerHelper;
import com.unco.reanimators.gui.rgui.util.Docking;

public class ActiveModules extends Label {
    public boolean sort_up = true;

    public ActiveModules() {
        super("");

        addRenderListener(new RenderListener() {
            @Override
            public void onPreRender() {
                Frame parentFrame = ContainerHelper.getFirstParent(Frame.class, ActiveModules.this);
                if (parentFrame == null) return;
                Docking docking = parentFrame.getDocking();
                if (docking.isTop()) sort_up = true;
                if (docking.isBottom()) sort_up = false;
            }

            @Override
            public void onPostRender() {}
        });
    }
};