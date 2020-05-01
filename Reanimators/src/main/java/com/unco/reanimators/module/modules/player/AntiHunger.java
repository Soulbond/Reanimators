package com.unco.reanimators.module.modules.player;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import com.unco.reanimators.event.events.PacketEvent;
import com.unco.reanimators.module.Module;
import net.minecraft.network.play.client.CPacketPlayer;

/**
 * Created by 086 on 8/04/2018. ofc kami
 */
@Module.Info(name = "AntiHunger", category = Module.Category.PLAYER, description = "Lose hunger less fast. Might cause ghostblocks.")
public class AntiHunger extends Module {

    @EventHandler
    public Listener<PacketEvent.Send> packetListener = new Listener<>(event -> {
        if (event.getPacket() instanceof CPacketPlayer) {
            ((CPacketPlayer) event.getPacket()).onGround = false;
        }
    });

}
