package com.unco.reanimators.module.modules.render;

import com.unco.reanimators.command.Command;
import com.unco.reanimators.module.Module;
import com.unco.reanimators.setting.Setting;
import com.unco.reanimators.setting.Settings;
import com.unco.reanimators.util.Friends;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.ScorePlayerTeam;

@Module.Info(name = "ExtraTab", description = "Expands the player tab menu", category = Module.Category.RENDER)
public class ExtraTab extends Module {

    public Setting<Integer> tabSize = register(Settings.integerBuilder("Players").withMinimum(1).withValue(136).build());

    public static ExtraTab INSTANCE;

    public ExtraTab() {
        ExtraTab.INSTANCE = this;
    }

    public static String getPlayerName(NetworkPlayerInfo networkPlayerInfoIn) {
        String dname = networkPlayerInfoIn.getDisplayName() != null ? networkPlayerInfoIn.getDisplayName().getFormattedText() : ScorePlayerTeam.formatPlayerName(networkPlayerInfoIn.getPlayerTeam(), networkPlayerInfoIn.getGameProfile().getName());
        if (Friends.isFriend(dname)) return String.format("%sa%s", Command.SECTIONSIGN(), dname);
        return dname;
    }
}