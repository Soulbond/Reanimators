package com.unco.reanimators.module.modules.combat;

import com.unco.reanimators.module.Module;
import com.unco.reanimators.module.Module.Info;

import net.minecraft.item.ItemBow;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.math.BlockPos;

import net.minecraft.client.Minecraft;
import com.unco.reanimators.util.Wrapper;

@Module.Info(name = "BowSpam", description = "FastBow", category = Module.Category.COMBAT)
public class BowSpam extends Module {
	public void onUpdate() {
		Minecraft mc = Wrapper.getMinecraft();

		if (mc.player.inventory.getCurrentItem().getItem() instanceof ItemBow) {
			if (mc.player.isHandActive() && mc.player.getItemInUseMaxCount() >= 3) {
				mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, mc.player.getHorizontalFacing()));
				mc.player.connection.sendPacket(new CPacketPlayerTryUseItem(mc.player.getActiveHand()));
				mc.player.stopActiveHand();
			}
		}
	}
}