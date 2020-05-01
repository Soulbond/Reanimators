package com.unco.reanimators.module.modules.player;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import com.unco.reanimators.event.events.PacketEvent;
import com.unco.reanimators.module.Module;
import net.minecraft.network.play.client.CPacketCloseWindow;

//clinet again

@Module.Info(name = "XCarry", category = Module.Category.PLAYER, description = "Store items in crafting slots")
public class XCarry extends Module {

    @EventHandler
    private Listener<PacketEvent.Send> listener = new Listener<>(event -> {
        if(event.getPacket() instanceof CPacketCloseWindow){
                event.cancel();
            }

    });
}
