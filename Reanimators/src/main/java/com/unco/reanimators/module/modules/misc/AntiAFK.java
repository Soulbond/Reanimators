package com.unco.reanimators.module.modules.misc;

import com.unco.reanimators.module.Module;
import com.unco.reanimators.setting.Setting;
import com.unco.reanimators.setting.Settings;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.util.EnumHand;

import java.util.Random;

/**
 * By 086
 */
@Module.Info(name = "AntiAFK", category = Module.Category.MISC, description = "Prevents being kicked for AFK")
public class AntiAFK extends Module {

    private Setting<Boolean> swing = register(Settings.b("Swing", true));
    private Setting<Boolean> turn = register(Settings.b("Turn", true));

    private Random random = new Random();

    @Override
    public void onUpdate() {
        if (mc.playerController.getIsHittingBlock()) return;

        if (mc.player.ticksExisted % 40 == 0 && swing.getValue())
            mc.getConnection().sendPacket(new CPacketAnimation(EnumHand.MAIN_HAND));
        if (mc.player.ticksExisted % 15 == 0 && turn.getValue())
            mc.player.rotationYaw = random.nextInt(360) - 180;

        if (!(swing.getValue() || turn.getValue()) && mc.player.ticksExisted % 80 == 0) {
            mc.player.jump();
        }
    }
}
