// 
// Decompiled by Procyon v0.5.36 gloom.
// 

package com.unco.reanimators.module.modules.misc;

import java.util.function.Predicate;
import com.unco.reanimators.util.Wrapper;
import net.minecraft.network.play.server.SPacketChat;
import me.zero.alpine.listener.EventHandler;
import com.unco.reanimators.event.events.PacketEvent;
import me.zero.alpine.listener.Listener;
import com.unco.reanimators.module.Module;

@Module.Info(name = "AutoReply", category = Module.Category.MISC, description = "automatically replies to messages")
public class AutoReply extends Module
{
    @EventHandler
    public Listener<PacketEvent.Receive> receiveListener;
    
    public AutoReply() {
        this.receiveListener = new Listener<PacketEvent.Receive>(event -> {
            if (event.getPacket() instanceof SPacketChat && ((SPacketChat)event.getPacket()).getChatComponent().getUnformattedText().contains("whispers:")) {
                Wrapper.getPlayer().sendChatMessage("/r oi im busy fuck off");
            }
        }, (Predicate<PacketEvent.Receive>[])new Predicate[0]);
    }
}
