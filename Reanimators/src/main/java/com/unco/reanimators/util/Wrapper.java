package com.unco.reanimators.util;

import com.unco.reanimators.gui.reanimators.ReanimatorsGUI;
import com.unco.reanimators.gui.rgui.render.font.FontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

public class Wrapper {

    private static FontRenderer fontRenderer;

    public static void init() {
        fontRenderer = ReanimatorsGUI.fontRenderer;
    }
    public static Minecraft getMinecraft() {
        return Minecraft.getMinecraft();
    }
    public static EntityPlayerSP getPlayer() {
        return getMinecraft().player;
    }
    public static World getWorld() {
        return getMinecraft().world;
    }
    public static int getKey(String keyname){
        return Keyboard.getKeyIndex(keyname.toUpperCase());
    }

    public static FontRenderer getFontRenderer() {
        return fontRenderer;
    }
}