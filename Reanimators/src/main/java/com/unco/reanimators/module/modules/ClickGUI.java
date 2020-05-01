package com.unco.reanimators.module.modules;

import com.unco.reanimators.gui.reanimators.DisplayGuiScreen;
import com.unco.reanimators.module.Module;
import org.lwjgl.input.Keyboard;

@Module.Info(name = "clickGUI", description = "Opens the Click GUI", category = Module.Category.HIDDEN)
public class ClickGUI extends Module {

    public ClickGUI() {
        getBind().setKey(Keyboard.KEY_P);
    }

    @Override
    protected int onEnable() {
        if (!(mc.currentScreen instanceof DisplayGuiScreen)) {
            mc.displayGuiScreen(new DisplayGuiScreen(mc.currentScreen));
        }
        disable();
        return 0;
    }

}
