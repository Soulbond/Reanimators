package com.unco.reanimators.module.modules.player;

import com.unco.reanimators.module.Module;

@Module.Info(name = "TpsSync", description = "Synchronizes some actions with the server TPS", category = Module.Category.PLAYER)
public class TpsSync extends Module {

    private static TpsSync INSTANCE;

    public TpsSync() {
        INSTANCE = this;
    }

    public static boolean isSync() {
        return INSTANCE.isEnabled();
    }

}