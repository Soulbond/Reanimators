// 
// Decompiled by Procyon v0.5.36, xor. aa.
// 

package com.unco.reanimators.module.modules.misc;

import java.util.function.Predicate;
import net.minecraft.client.gui.GuiScreen;
import com.unco.reanimators.command.Command;
import net.minecraft.client.gui.GuiGameOver;
import com.unco.reanimators.setting.Settings;
import me.zero.alpine.listener.EventHandler;
import com.unco.reanimators.event.events.GuiScreenEvent;
import me.zero.alpine.listener.Listener;
import com.unco.reanimators.setting.Setting;
import com.unco.reanimators.module.Module;

@Module.Info(name = "AutoRespawn", description = "Automatically respawns upon death and tells you where you died", category = Module.Category.PLAYER)
public class AutoRespawn extends Module
{
    private Setting<Boolean> deathCoords;
    private Setting<Boolean> respawn;
    @EventHandler
    public Listener<GuiScreenEvent.Displayed> listener;
    
    public AutoRespawn() {
        this.deathCoords = this.register(Settings.b("DeathCoords", false));
        this.respawn = this.register(Settings.b("Respawn", true));
        this.listener = new Listener<GuiScreenEvent.Displayed>(event -> {
            if (event.getScreen() instanceof GuiGameOver) {
                if (this.deathCoords.getValue()) {
                    Command.sendChatMessage(String.format("You died at x %d y %d z %d", (int)AutoRespawn.mc.player.posX, (int)AutoRespawn.mc.player.posY, (int)AutoRespawn.mc.player.posZ));
                }
                if (this.respawn.getValue()) {
                    AutoRespawn.mc.player.respawnPlayer();
                    AutoRespawn.mc.displayGuiScreen((GuiScreen)null);
                }
            }
        }, (Predicate<GuiScreenEvent.Displayed>[])new Predicate[0]);
    }
}
