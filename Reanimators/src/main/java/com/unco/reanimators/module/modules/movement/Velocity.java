package com.unco.reanimators.module.modules.movement;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import com.unco.reanimators.event.ReanimatorsEvent;
import com.unco.reanimators.event.events.EntityEvent;
import com.unco.reanimators.event.events.PacketEvent;
import com.unco.reanimators.module.Module;
import com.unco.reanimators.setting.Setting;
import com.unco.reanimators.setting.Settings;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;

@Module.Info(name = "Velocity", description = "Modify knockback.", category = Module.Category.MOVEMENT)
public class Velocity extends Module {
	private Setting<Float> horizontal = register(Settings.f("Horizontal", 0));
	private Setting<Float> vertical = register(Settings.f("Vertical", 0));

	@EventHandler
	private Listener<PacketEvent.Receive> packetEventListener = new Listener<>(event -> {
		if (event.getEra() == ReanimatorsEvent.Era.PRE) {
            if (event.getPacket() instanceof SPacketEntityVelocity) {
                SPacketEntityVelocity velocity = (SPacketEntityVelocity) event.getPacket();
                if (velocity.getEntityID() == mc.player.entityId) {
                    if (horizontal.getValue() == 0 && vertical.getValue() == 0) event.cancel();
                    velocity.motionX *= horizontal.getValue();
                    velocity.motionY *= vertical.getValue();
                    velocity.motionZ *= horizontal.getValue();
                }
            } else if (event.getPacket() instanceof SPacketExplosion) {
                if (horizontal.getValue() == 0 && vertical.getValue() == 0) event.cancel();
                SPacketExplosion velocity = (SPacketExplosion) event.getPacket();
                velocity.motionX *= horizontal.getValue();
                velocity.motionY *= vertical.getValue();
                velocity.motionZ *= horizontal.getValue();
            }
        }
    });

    @EventHandler
    private Listener<EntityEvent.EntityCollision> entityCollisionListener = new Listener<>(event -> {
        if (event.getEntity() == mc.player) {
            if (horizontal.getValue() == 0 && vertical.getValue() == 0) {
                event.cancel();
                return;
            }
            event.setX(-event.getX() * horizontal.getValue());
            event.setY(0);
            event.setZ(-event.getZ() * horizontal.getValue());
        }
	});
}