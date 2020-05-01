package com.unco.reanimators.module.modules.combat;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import com.unco.reanimators.command.Command;
import com.unco.reanimators.event.events.PacketEvent;
import com.unco.reanimators.module.Module;
import com.unco.reanimators.setting.Setting;
import com.unco.reanimators.setting.Settings;
import net.minecraft.init.Items;

/**
 * Created 17 October 2019 by hub
 * Updated 21 November 2019 by hub
 */
@Module.Info(name = "FastAutoXP", category = Module.Category.COMBAT, description = "Auto Switch to XP and throw fast")
public class FastAutoXP extends Module {

    private Setting<Boolean> autoThrow = register(Settings.b("Auto Throw", true));
    private Setting<Boolean> autoSwitch = register(Settings.b("Auto Switch", true));
    private Setting<Boolean> autoDisable = register(Settings.booleanBuilder("Auto Disable").withValue(true).withVisibility(o -> autoSwitch.getValue()).build());

    private int initHotbarSlot = -1;

    @EventHandler
    private Listener<PacketEvent.Receive> receiveListener = new Listener<>(event ->
    {
        if (mc.player != null && (mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE)) {
            mc.rightClickDelayTimer = 0;
        }
    });

    protected void onEnabled() {

        if (mc.player == null) {
            return;
        }

        if (autoSwitch.getValue()) {
            initHotbarSlot = mc.player.inventory.currentItem;
        }

    }

    @Override
    protected void onDisable() {

        if (mc.player == null) {
            return;
        }

        if (autoSwitch.getValue()) {
            if (initHotbarSlot != -1 && initHotbarSlot != mc.player.inventory.currentItem) {
                mc.player.inventory.currentItem = initHotbarSlot;
            }
        }

    }

    @Override
    public void onUpdate() {

        if (mc.player == null) {
            return;
        }

        if (autoSwitch.getValue() && (mc.player.getHeldItemMainhand().getItem() != Items.EXPERIENCE_BOTTLE)) {
            int xpSlot = findXpPots();
            if (xpSlot == -1) {
                if (autoDisable.getValue()) {
                    Command.sendChatMessage("[FastAutoXP] No XP in hotbar, disabling");
                    this.disable();
                }
                return;
            }
            mc.player.inventory.currentItem = xpSlot;
        }

        if (autoThrow.getValue() && mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE) {
            mc.rightClickMouse();
        }

    }

    private int findXpPots() {
        int slot = -1;
        for (int i = 0; i < 9; i++) {
            if (mc.player.inventory.getStackInSlot(i).getItem() == Items.EXPERIENCE_BOTTLE) {
                slot = i;
                break;
            }
        }
        return slot;
    }

}
