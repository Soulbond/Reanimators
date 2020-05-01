package com.unco.reanimators.module.modules.render;

import com.unco.reanimators.module.Module;
import com.unco.reanimators.setting.Setting;
import com.unco.reanimators.setting.Settings;

@Module.Info(name = "AntiFog", description = "Disables or reduces fog", category = Module.Category.RENDER)
public class AntiFog extends Module {
    public static Setting<VisionMode> mode = Settings.e("Mode", VisionMode.NOFOG);
    private static AntiFog INSTANCE = new AntiFog();

    public AntiFog() {
        INSTANCE = this;
        register(mode);
    }

    public static boolean enabled() {
        return INSTANCE.isEnabled();
    }

    public enum VisionMode {
        NOFOG, AIR
    }

}