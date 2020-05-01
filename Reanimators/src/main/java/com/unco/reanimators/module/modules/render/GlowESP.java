// 
// Decompiled by Procyon v0.5.36, xor. aaaA.
// 

package com.unco.reanimators.module.modules.render;

import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import com.unco.reanimators.util.Wrapper;
import com.unco.reanimators.module.Module;

@Module.Info(name = "Glow ESP", category = Module.Category.RENDER, description = "Gives players glowing effect")
public class GlowESP extends Module
{
    @Override
    public void onUpdate() {
        if (Wrapper.getMinecraft().getRenderManager().options == null) {
            return;
        }
        for (final Entity entity : GlowESP.mc.world.loadedEntityList) {
            if (entity instanceof EntityPlayer && !entity.isGlowing()) {
                entity.setGlowing(true);
            }
        }
    }
    
    public void onDisable() {
        for (final Entity entity : GlowESP.mc.world.loadedEntityList) {
            if (entity instanceof EntityPlayer && entity.isGlowing()) {
                entity.setGlowing(false);
            }
        }
        super.onDisable();
    }
}
