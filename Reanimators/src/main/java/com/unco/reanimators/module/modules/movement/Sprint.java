package com.unco.reanimators.module.modules.movement;

import com.unco.reanimators.module.Module;

/**
 * By 086
 */
@Module.Info(name = "Sprint", description = "Automatically makes the player sprint", category = Module.Category.MOVEMENT)
public class Sprint extends Module {

    @Override
    public void onUpdate() {
        try {
            if (!mc.player.collidedHorizontally && mc.player.moveForward > 0)
                mc.player.setSprinting(true);
            else
                mc.player.setSprinting(false);
        } catch (Exception ignored) {}
    }

}
