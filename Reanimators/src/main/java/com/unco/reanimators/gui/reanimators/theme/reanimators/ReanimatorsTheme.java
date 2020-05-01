package com.unco.reanimators.gui.reanimators.theme.reanimators;

import com.unco.reanimators.gui.reanimators.ReanimatorsGUI;
import com.unco.reanimators.gui.reanimators.theme.staticui.TabGuiUI;
import com.unco.reanimators.gui.rgui.component.container.use.Frame;
import com.unco.reanimators.gui.rgui.component.use.Button;
import com.unco.reanimators.gui.rgui.render.AbstractComponentUI;
import com.unco.reanimators.gui.rgui.render.font.FontRenderer;
import com.unco.reanimators.gui.rgui.render.theme.AbstractTheme;

public class ReanimatorsTheme extends AbstractTheme {
    FontRenderer fontRenderer;

    public ReanimatorsTheme() {
        installUI(new RootButtonUI<Button>());
        installUI(new GUIUI());
        installUI(new RootGroupboxUI());
        installUI(new ReanimatorsFrameUI<Frame>());
        installUI(new RootScrollpaneUI());
        installUI(new RootInputFieldUI());
        installUI(new RootLabelUI());
        installUI(new RootChatUI());
        installUI(new RootCheckButtonUI());
        installUI(new ReanimatorsActiveModulesUI());
        installUI(new ReanimatorsSettingsPanelUI());
        installUI(new RootSliderUI());
        installUI(new ReanimatorsEnumbuttonUI());
        installUI(new RootColorizedCheckButtonUI());
        installUI(new ReanimatorsUnboundSliderUI());

        installUI(new TabGuiUI());

        fontRenderer = ReanimatorsGUI.fontRenderer;
    }

    @Override
    public FontRenderer getFontRenderer() {
        return fontRenderer;
    }

    public class GUIUI extends AbstractComponentUI<ReanimatorsGUI> {
    }
}
