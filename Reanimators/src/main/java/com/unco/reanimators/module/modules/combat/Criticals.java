package com.unco.reanimators.module.modules.combat;

import me.zero.alpine.listener.Listener;
import com.unco.reanimators.module.Module;
import me.zero.alpine.listener.EventHandler;
import com.unco.reanimators.event.events.PacketEvent;

import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.client.CPacketPlayer;

// Rina.
@Module.Info(name = "Criticals", category = Module.Category.COMBAT)
public class Criticals extends Module {
	private Listener<PacketEvent.Send>packetEvent = new Listener<>(event -> {
		if (event.getPacket() instanceof CPacketUseEntity) {
			if (((CPacketUseEntity) event.getPacket()).getAction() == CPacketUseEntity.Action.ATTACK) {
				mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.1f, mc.player.posZ, false));
				mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY, mc.player.posZ, false));
			}
		}
	});
}
