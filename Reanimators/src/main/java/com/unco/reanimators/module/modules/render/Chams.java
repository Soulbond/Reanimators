package com.unco.reanimators.module.modules.render;

import com.unco.reanimators.module.Module;
import com.unco.reanimators.setting.Setting;
import com.unco.reanimators.setting.Settings;
import com.unco.reanimators.util.EntityUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

@Module.Info(name = "Chams", category = Module.Category.RENDER, description = "See entities through walls")
public class Chams extends Module {

    private static Setting<Boolean> players = Settings.b("Players", true);
    private static Setting<Boolean> animals = Settings.b("Animals", false);
    private static Setting<Boolean> mobs = Settings.b("Mobs", false);

    public Chams() {
        registerAll(players, animals, mobs);
    }

    public static boolean renderChams(Entity entity) {
        return (entity instanceof EntityPlayer ? players.getValue() : (EntityUtil.isPassive(entity) ? animals.getValue() : mobs.getValue()));
    }

}