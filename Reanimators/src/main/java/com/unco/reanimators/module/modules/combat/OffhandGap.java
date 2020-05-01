package com.unco.reanimators.module.modules.combat;

import com.unco.reanimators.module.Module;
import com.unco.reanimators.module.ModuleManager;
import com.unco.reanimators.setting.Setting;
import com.unco.reanimators.setting.Settings;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;

@Module.Info(name = "OffhandGap", category = Module.Category.COMBAT)
public class OffhandGap extends Module {
	private Setting<Boolean> totem_disable = register(Settings.b("Totem Disable", true));

	int gapples;
	boolean get_gapple = false;
	boolean get_move = false;

	@Override
	public int onEnable() {
		if (totem_disable.getValue()) {
			ModuleManager.getModuleByName("AutoTotem").disable();
		} else {
            return 0;
		}
        return 0;
    }

	@Override
	public void onDisable() {
		if (totem_disable.getValue()) {
			ModuleManager.getModuleByName("AutoTotem").enable();
		} else {
			return;
		}
	}

	@Override
	public void onUpdate() {
		if (mc.currentScreen instanceof GuiContainer) return;
		if (get_gapple) {
			int t = -1;
			for (int i = 0; i < 45; i++)
				if (mc.player.inventory.getStackInSlot(i).isEmpty) {
					t = i;
					break;
				}	
			if (t == -1) return;
			mc.playerController.windowClick(0, t < 9 ? t + 36 : t, 0, ClickType.PICKUP, mc.player);
			get_gapple = false;
		}

		gapples = mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.GOLDEN_APPLE).mapToInt(ItemStack::getCount).sum();
		
		if (mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE){
			return;
		} else {
			if (get_move) {
				mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, mc.player);
				get_move = false;
				if (!mc.player.inventory.itemStack.isEmpty()) get_gapple = true;
				return;
			}

			if (mc.player.inventory.itemStack.isEmpty()) {
				if (gapples == 0) return;
				int t = -1;
				for (int i = 0; i < 45; i++)
					if (mc.player.inventory.getStackInSlot(i).getItem() == Items.GOLDEN_APPLE) {
						t = i;
						break;
					}
				if (t == -1) return;

				mc.playerController.windowClick(0, t < 9 ? t + 36 : t, 0, ClickType.PICKUP, mc.player);
				get_move = true;
			}
		}
	}
}