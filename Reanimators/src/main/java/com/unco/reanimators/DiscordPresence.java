package com.unco.reanimators;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import com.unco.reanimators.module.ModuleManager;
import com.unco.reanimators.Reanimators;
import com.unco.reanimators.module.modules.misc.DiscordSettings;
import org.lwjgl.opengl.APPLEAuxDepthStencil;
import scala.App;

/***
 * @author S-B99
 * Updated by S-B99 on 13/01/20
 */
public class DiscordPresence {
    private static final String APP_ID ="691034100873822268";
    public static DiscordRichPresence presence;
    private static boolean hasStarted;
    private static final DiscordRPC rpc;
    private static String details;
    private static String state;
    private static DiscordSettings discordSettings;


    public static void start() {
        Reanimators.log.info("Starting Discord RPC");
        if (DiscordPresence.hasStarted) return;
        DiscordPresence.hasStarted = true;

        final DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.disconnected = ((var1, var2) -> Reanimators.log.info("Discord RPC disconnected, var1: " + var1 + ", var2: " + var2));
        DiscordPresence.rpc.Discord_Initialize(APP_ID, handlers, true, "");
        DiscordPresence.presence.startTimestamp = System.currentTimeMillis() / 1000L;

        /* update rpc normally */
        setRpcFromSettings();

        /* update rpc while thread isn't interrupted  */
        new Thread(DiscordPresence::setRpcFromSettingsNonInt, "Discord-RPC-Callback-Handler").start();
        Reanimators.log.info("Discord RPC initialised successfully");
    }

    private static void setRpcFromSettingsNonInt() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                DiscordPresence.rpc.Discord_RunCallbacks();
                discordSettings = ((DiscordSettings) ModuleManager.getModuleByName("DiscordRPC"));
                String separator = " | ";
                details = discordSettings.getLine(discordSettings.line1Setting.getValue()) + separator + discordSettings.getLine(discordSettings.line3Setting.getValue());
                state = discordSettings.getLine(discordSettings.line2Setting.getValue()) + separator + discordSettings.getLine(discordSettings.line4Setting.getValue());
                DiscordPresence.presence.details = details;
                DiscordPresence.presence.state = state;
                DiscordPresence.rpc.Discord_UpdatePresence(DiscordPresence.presence);
            }
            catch (Exception e2) { e2.printStackTrace(); }
            try { Thread.sleep(4000L); }
            catch (InterruptedException e3) { e3.printStackTrace(); }
        }
    }
    private static void setRpcFromSettings() {
        discordSettings = ((DiscordSettings) ModuleManager.getModuleByName("DiscordRPC"));
        details = discordSettings.getLine(discordSettings.line1Setting.getValue()) + " " + discordSettings.getLine(discordSettings.line3Setting.getValue());
        state = discordSettings.getLine(discordSettings.line2Setting.getValue()) + " " + discordSettings.getLine(discordSettings.line4Setting.getValue());
        DiscordPresence.presence.details = details;
        DiscordPresence.presence.state = state;
        DiscordPresence.presence.largeImageKey = "pack";
        DiscordPresence.presence.largeImageText = "Reanimators Client";
        DiscordPresence.rpc.Discord_UpdatePresence(DiscordPresence.presence);
    }

    static {
        rpc = DiscordRPC.INSTANCE;
        DiscordPresence.presence = new DiscordRichPresence();
        DiscordPresence.hasStarted = false;
    }

    /* I have no idea how to disconnect rpc properly atm */
//    private static /* synthetic */ void lambdastart1() {
//        setRpcSettings();
//    }
//
//    private static /* synthetic */ void lambdastart0(final int var1, final String var2) {
//        System.out.println("Discord RPC disconnected, var1: " + var1 + ", var2: " + var2);
//    }
}
