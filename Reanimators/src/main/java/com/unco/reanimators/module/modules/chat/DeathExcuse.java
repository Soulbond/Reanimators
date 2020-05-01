package com.unco.reanimators.module.modules.chat;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import com.unco.reanimators.module.Module;

@Module.Info(
   name = "DeathExcuse",
   description = "Reason You Died",
   category = Module.Category.CHAT
)
public class DeathExcuse extends Module {
   private int delay = 0;

   public void onUpdate() {
      ++this.delay;
      List myList = Arrays.asList("Lag", "desync", "LOL DESYNC", "WOW GOT DDOSED RIGHT THERE", "Wow Died Because of Ping", "Stupid Ping Players", "Ghost Block");
      Random r = new Random();
      int randomitem = r.nextInt(myList.size());
      String randomElement = (String)myList.get(randomitem);
      if (mc.player.isDead) {
         this.delay = 20000000;
      }

      if (this.delay > 20000100) {
         mc.player.sendChatMessage(randomElement);
         this.delay = 0;
      }

   }
}
