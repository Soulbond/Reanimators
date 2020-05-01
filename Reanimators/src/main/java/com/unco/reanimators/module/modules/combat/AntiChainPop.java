package com.unco.reanimators.module.modules.combat;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import com.unco.reanimators.Reanimators;
import com.unco.reanimators.command.Command;
import com.unco.reanimators.event.events.PacketEvent;
import com.unco.reanimators.event.events.TotemPopEvent;
import com.unco.reanimators.module.Module;
import com.unco.reanimators.module.ModuleManager;
import com.unco.reanimators.setting.Setting;
import com.unco.reanimators.setting.Settings;
import com.unco.reanimators.util.Wrapper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.SPacketEntityStatus;

/*
 * Hamburger made 21/02/2020. clinet
 */

@Module.Info(name = "AntiChainPop", description = "Stops Chain pops of Totems", category = Module.Category.COMBAT)
public class AntiChainPop extends Module {


    Module m;


    @EventHandler
    public Listener<TotemPopEvent> totemPopEvent = new Listener<>(event -> {
        EntityPlayer entity = (EntityPlayer) event.getEntity();
        if (mc.player == entity) {
            m = ModuleManager.getModuleByName("Surround");
            if (!ModuleManager.isModuleEnabled("Surround")) m.toggle();
        }
    });

    @EventHandler
    public Listener<PacketEvent.Receive> totemPopListener = new Listener<>(event -> {

        if (mc.world == null || mc.player == null) {
            return;
        }

        if (event.getPacket() instanceof SPacketEntityStatus) {
            SPacketEntityStatus packet = (SPacketEntityStatus) event.getPacket();
            if (packet.getOpCode() == 35) {
                Entity entity = packet.getEntity(mc.world);
                Reanimators.EVENT_BUS.post(new TotemPopEvent(entity));
            }
        }

    });
}
