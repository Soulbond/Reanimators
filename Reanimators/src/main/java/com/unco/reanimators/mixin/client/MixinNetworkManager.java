package com.unco.reanimators.mixin.client;

import io.netty.channel.ChannelHandlerContext;
import com.unco.reanimators.Reanimators;
import com.unco.reanimators.event.events.PacketEvent;
import com.unco.reanimators.module.modules.misc.NoPackKick;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(NetworkManager.class)
public class MixinNetworkManager {

    @Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At("HEAD"), cancellable = true)
    private void onSendPacket(Packet<?> packet, CallbackInfo callbackInfo) {
        PacketEvent event = new PacketEvent.Send(packet);
        Reanimators.EVENT_BUS.post(event);

        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    private void onChannelRead(ChannelHandlerContext context, Packet<?> packet, CallbackInfo callbackInfo) {
        PacketEvent event = new PacketEvent.Receive(packet);
        Reanimators.EVENT_BUS.post(event);

        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method = "exceptionCaught", at = @At("HEAD"), cancellable = true)
    private void exceptionCaught(ChannelHandlerContext p_exceptionCaught_1_, Throwable p_exceptionCaught_2_, CallbackInfo info) {
        if (p_exceptionCaught_2_ instanceof IOException && NoPackKick.isEnabled()) info.cancel();
    }

}
