package com.unco.reanimators.reanimatorsrpc;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRichPresence;
import club.minnced.discord.rpc.DiscordRPC;

import com.unco.reanimators.module.ModuleManager;

public class ReanimatorsDiscordRPC {
	public static DiscordRichPresence discord_presence;

	private static boolean discord_started;
	private static final DiscordRPC discord_rpc;

	public static String detail;
	public static String state;

	public static void start() {
		if (ReanimatorsDiscordRPC.discord_started) return;
		ReanimatorsDiscordRPC.discord_started = true;

		final DiscordEventHandlers handler_ = new DiscordEventHandlers();
		ReanimatorsDiscordRPC.discord_rpc.Discord_Initialize("691034100873822268", handler_, true, "");
		ReanimatorsDiscordRPC.discord_presence.startTimestamp = System.currentTimeMillis() / 1000l;

		ReanimatorsDiscordRPC.discord_presence.largeImageKey = "pack";
		ReanimatorsDiscordRPC.discord_presence.largeImageText = "pack";

		new Thread(ReanimatorsDiscordRPC::discordRpcInit).start();
	}

	private static void discordRpcInit() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				ReanimatorsDiscordRPC.discord_rpc.Discord_RunCallbacks();

				ReanimatorsDiscordRPC.discord_presence.details = detail;
				ReanimatorsDiscordRPC.discord_presence.state = state;

				ReanimatorsDiscordRPC.discord_rpc.Discord_UpdatePresence(ReanimatorsDiscordRPC.discord_presence);
			}

			catch (Exception exc) {
				exc.printStackTrace();
			}

			try {
				Thread.sleep(4000L);
			}

			catch (InterruptedException exc_) {
				exc_.printStackTrace();
			}
		}
	}

	static {
		discord_rpc = DiscordRPC.INSTANCE;
		ReanimatorsDiscordRPC.discord_presence = new DiscordRichPresence();
		ReanimatorsDiscordRPC.discord_started = false;
	}
}