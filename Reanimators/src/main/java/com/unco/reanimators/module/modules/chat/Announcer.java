package com.unco.reanimators.module.modules.chat;

import com.unco.reanimators.module.Module;
import com.unco.reanimators.setting.Setting;
import com.unco.reanimators.setting.Settings;
import net.minecraft.init.Items;

@Module.Info(
   name = "Announcer",
   description = "how to get /ignored in one click.",
   category = Module.Category.CHAT
)
public class Announcer extends Module {
   private Setting delays;
   private int delay = 0;
   private Setting DelayChange = this.register(Settings.integerBuilder("SecondDelay").withRange(1, 100).withValue((int)10).build());
   private Setting testchange = this.register(Settings.stringBuilder("Test").withValue("aple").build());

   public void onUpdate() {
      ++this.delay;
      if (this.delay > (Integer)this.DelayChange.getValue() * 40) {
         if (mc.player.sleeping) {
            mc.player.sendChatMessage("im sleepin thanks to reanimators!");
         }

         if (mc.player.inWater) {
            mc.player.sendChatMessage("im treading water like a champ thanks to reanimators!");
         }

         if (mc.player.isDead) {
            mc.player.sendChatMessage("i ded :( not reanimators fault tho");
         }

         if (mc.player.isInWeb) {
            mc.player.sendChatMessage("im in webs rn, thanks reanimators!");
         }

         if (mc.player.inPortal) {
            mc.player.sendChatMessage("i be goin thru a portal rn, thanks reanimators!");
         }

         if (mc.player.isSneaking()) {
            mc.player.sendChatMessage("im sneakin thanks to reanimators!");
         }

         if (mc.player.isElytraFlying()) {
            mc.player.sendChatMessage("heck yea, im flyin thru the air like a butterfly thanks to reanimators");
         }

         if (mc.fpsCounter == 144) {
            mc.player.sendChatMessage("thanks to reanimators im at 144 fps!");
         }

         if (mc.player.isInLava()) {
            mc.player.sendChatMessage("im in lava rn, thanks reanimators!");
         }

         if (mc.player.isSwingInProgress && mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL) {
            mc.player.sendChatMessage("i be shredding you with crystals with reanimators");
         }

         if (mc.world.isRaining()) {
            mc.player.sendChatMessage("oh boy its raining a storm out there, thanks reaimators!");
         }

         if (mc.playerController.isHittingBlock && mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_PICKAXE) {
            mc.player.sendChatMessage("im mining rn, thanks reanimators!");
         }

         if (mc.player.isSwingInProgress && mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_SWORD) {
            mc.player.sendChatMessage("im tearing you to pieces thanks to reanimators");
         }

         if (mc.player.getHealth() == 10.0F && mc.player.getHeldItemMainhand().getItem() == Items.GOLDEN_APPLE) {
            mc.player.sendChatMessage("im munching on gaps thanks to reanimators!");
         }

         if (mc.player.getLastAttackedEntityTime() == 1) {
            mc.player.sendChatMessage("oh hell yea, im on full health thanks to reanimators!");
         }

         if (mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE) {
            mc.player.sendChatMessage("yee haw im throwin xp thanks to reanimators!!");
         }

         this.delay = 0;
      }

   }
}
