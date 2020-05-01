package com.unco.reanimators.module.modules.movement;

import com.unco.reanimators.module.Module;

@Module.Info(name = "SafeWalk", category = Module.Category.MOVEMENT, description = "Keeps you from walking off edges")
public class SafeWalk extends Module {

    private static SafeWalk INSTANCE;

    public SafeWalk() {
        INSTANCE = this;
    }

    public static boolean shouldSafewalk() {
        return INSTANCE.isEnabled();
    }
}