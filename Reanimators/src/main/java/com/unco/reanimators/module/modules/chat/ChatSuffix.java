package com.unco.reanimators.module.modules.chat;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import com.unco.reanimators.event.events.PacketEvent;
import com.unco.reanimators.module.Module;
import com.unco.reanimators.setting.Setting;
import com.unco.reanimators.setting.Settings;

import net.minecraft.network.play.client.CPacketChatMessage;

// Rina, modified
@Module.Info(name = "ChatSuffix", category = Module.Category.CHAT, description = "Modifies your chat messages")
public class ChatSuffix extends Module {
    private Setting<Boolean> commands = register(Settings.b("Commands", true));
    private Setting<Boolean> reanimatorsSuffix = register(Settings.b("Reanimators Suffix", true));

    private final String REANIMATORS_SUFFIX = " \u23D0 \u0280\u1d07\u1d00\u0274\u026a\u1d0d\u1d00\u1d1b\u1d0f\u0280\ua731";

    boolean suffix_accept;
    String suffix;

    @EventHandler
    public Listener<PacketEvent.Send> listener = new Listener<>(event -> {
        if (event.getPacket() instanceof CPacketChatMessage) {         
            if (reanimatorsSuffix.getValue()) {
                suffix = REANIMATORS_SUFFIX;
                suffix_accept = true;
            } 

            String msg = ((CPacketChatMessage) event.getPacket()).getMessage();

            if (msg.startsWith("/") && commands.getValue()) suffix_accept = false; // Ignore.
            if (msg.startsWith("&") && commands.getValue()) suffix_accept = false; // Ignore.
            if (msg.startsWith("?") && commands.getValue()) suffix_accept = false; // Ignore.
            if (msg.startsWith("!") && commands.getValue()) suffix_accept = false; // Ignore.
            if (msg.startsWith(":") && commands.getValue()) suffix_accept = false; // Ignore.
            if (msg.startsWith(";") && commands.getValue()) suffix_accept = false; // Ignore.
            if (msg.startsWith(".") && commands.getValue()) suffix_accept = false; // Ignore.
            if (msg.startsWith(",") && commands.getValue()) suffix_accept = false; // Ignore.
            if (msg.startsWith("-") && commands.getValue()) suffix_accept = false; // Ignore.
            if (msg.startsWith("_") && commands.getValue()) suffix_accept = false; // Ignore.
            if (msg.startsWith("#") && commands.getValue()) suffix_accept = false; // Ignore.

            if (suffix_accept == true){
                msg += suffix;
            } else {
                return;
            }
                
            if (msg.length() >= 256) msg = msg.substring(0, 256);
            ((CPacketChatMessage) event.getPacket()).message = msg;
        }
    });

}