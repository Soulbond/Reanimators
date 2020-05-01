package com.unco.reanimators.module.modules.render;

import com.unco.reanimators.module.Module;

/**
 * 086
 */
@Module.Info(name = "AntiWeather", description = "Removes rain from your world", category = Module.Category.RENDER)
public class AntiWeather extends Module {

    @Override
    public void onUpdate() {
        if (isDisabled()) return;
        if (mc.world.isRaining())
            mc.world.setRainStrength(0);
    }
}
