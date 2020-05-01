package com.unco.reanimators.module.modules.player;

import com.unco.reanimators.module.Module;


//clinet skid

@Module.Info(name = "Suicide (KILLS YOU!)", category = Module.Category.PLAYER, description = "Kills self by running the /kill command")
public class Suicide extends Module {

    public void onEnabled() {
        mc.player.sendChatMessage("/kill");
        this.disable();
        }

}


