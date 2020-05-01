package com.unco.reanimators.module.modules.misc;

import com.unco.reanimators.DiscordPresence;
import com.unco.reanimators.Reanimators;
import com.unco.reanimators.module.Module;
import com.unco.reanimators.setting.Setting;
import com.unco.reanimators.setting.Settings;

/**
 * @author S-B99
 * Updated by S-B99 on 13/01/20
 */
@Module.Info(name = "DiscordRPC", category = Module.Category.MISC, description = "Discord Rich Presence")
public class DiscordSettings extends Module {

    public Setting<Boolean> startupGlobal = register(Settings.b("Enable Automatically", true));
    public Setting<Boolean> coordsConfirm = register(Settings.b("Coords Confirm", false));
    public Setting<LineInfo> line1Setting = register(Settings.e("Line 1 Left", LineInfo.VERSION)); // details left
    public Setting<LineInfo> line3Setting = register(Settings.e("Line 1 Right", LineInfo.SERVERIP)); // details right
    public Setting<LineInfo> line2Setting = register(Settings.e("Line 2 Left", LineInfo.USERNAME)); // state left
    public Setting<LineInfo> line4Setting = register(Settings.e("Line 2 Right", LineInfo.HEALTH)); // state right

    public enum LineInfo {
        VERSION, WORLD, USERNAME, HEALTH, SERVERIP, COORDS, PROMO, NONE
    }

    public String getLine(LineInfo line) {
        switch (line) {
            case VERSION: return Reanimators.MODVER;
            case WORLD:
                if (mc.isIntegratedServerRunning()) return "Singleplayer";
                else if (mc.getCurrentServerData() != null) return "Multiplayer";
                else return "Main Menu";
            case USERNAME:
                if (mc.player != null) return mc.player.getName();
                else return "(Not logged in)";
            case HEALTH:
                if (mc.player != null) return "(" + ((int) mc.player.getHealth()) + " hp)";
                else return "(No hp)";
            case SERVERIP:
                if (mc.getCurrentServerData() != null) return mc.getCurrentServerData().serverIP;
                else return "(Offline)";
            case COORDS:
                if (mc.player != null && coordsConfirm.getValue()) return "(" + (int) mc.player.posX + " " + (int) mc.player.posY + " " + (int) mc.player.posZ + ")";
                else return "(No coords)";
            default: return "";
            case PROMO:
                if (mc.player !=null)return "Reanimators best client";
                else return "https://discord.gg/hSb5Qh2";
        }
    }

    @Override
    public int onEnable() {
        DiscordPresence.start();
        return 0;
    }

    private static long startTime = 0;
    @Override
    public void onUpdate() {
        if (startTime == 0) startTime = System.currentTimeMillis();
        if (startTime + 10000 <= System.currentTimeMillis()) { // 10 seconds in milliseconds
            if (line1Setting.getValue().equals(LineInfo.COORDS) || line2Setting.getValue().equals(LineInfo.COORDS) || line3Setting.getValue().equals(LineInfo.COORDS) || line4Setting.getValue().equals(LineInfo.COORDS)) {
                if (!coordsConfirm.getValue() && mc.player != null) {
                }
            }
            startTime = System.currentTimeMillis();
        }
    }

}
