// 
// Decompiled by Procyon v0.5.36, xor again
// 

package com.unco.reanimators.module.modules.combat;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.item.ItemTool;
import java.util.function.Predicate;
import me.zero.alpine.listener.EventHandler;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import me.zero.alpine.listener.Listener;
import com.unco.reanimators.module.Module;

@Module.Info(name = "AutoSword", description = "Automatically switch to the best Weapon when Attacking", category = Module.Category.COMBAT)
public class AutoSword extends Module
{
    @EventHandler
    private Listener<AttackEntityEvent> attackListener;
    
    public AutoSword() {
        this.attackListener = new Listener<AttackEntityEvent>(event -> equipBestWeapon(), (Predicate<AttackEntityEvent>[])new Predicate[0]);
    }
    
    public static void equipBestWeapon() {
        int bestSlot = -1;
        double maxDamage = 0.0;
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = AutoSword.mc.player.inventory.getStackInSlot(i);
            if (!stack.isEmpty) {
                if (stack.getItem() instanceof ItemTool) {
                    final double damage = ((ItemTool)stack.getItem()).attackDamage + (double)EnchantmentHelper.getModifierForCreature(stack, EnumCreatureAttribute.UNDEFINED);
                    if (damage > maxDamage) {
                        maxDamage = damage;
                        bestSlot = i;
                    }
                }
                else if (stack.getItem() instanceof ItemSword) {
                    final double damage = ((ItemSword)stack.getItem()).getAttackDamage() + (double)EnchantmentHelper.getModifierForCreature(stack, EnumCreatureAttribute.UNDEFINED);
                    if (damage > maxDamage) {
                        maxDamage = damage;
                        bestSlot = i;
                    }
                }
            }
        }
        if (bestSlot != -1) {
            equip(bestSlot);
        }
    }
    
    private static void equip(final int slot) {
        AutoSword.mc.player.inventory.currentItem = slot;
        AutoSword.mc.playerController.syncCurrentPlayItem();
    }
}
