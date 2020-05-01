package com.unco.reanimators.module.modules.misc;

import com.unco.reanimators.module.Module;
import com.unco.reanimators.setting.Setting;
import com.unco.reanimators.setting.Settings;

@Module.Info(name = "NoEntityTrace", category = Module.Category.MISC, description = "Blocks entities from stopping you from mining")
public class NoEntityTrace extends Module {

    private Setting<TraceMode> mode = register(Settings.e("Mode", TraceMode.DYNAMIC));

    private static NoEntityTrace INSTANCE;

    public NoEntityTrace() {
        NoEntityTrace.INSTANCE = this;
    }

    public static boolean shouldBlock() {
        return INSTANCE.isEnabled() && (INSTANCE.mode.getValue() == TraceMode.STATIC || mc.playerController.isHittingBlock);
    }

    private enum TraceMode {
        STATIC, DYNAMIC
    }
}