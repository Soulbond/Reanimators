package com.unco.reanimators.event.events;

import com.unco.reanimators.event.ReanimatorsEvent;
import net.minecraft.network.play.server.SPacketChunkData;
import net.minecraft.world.chunk.Chunk;

public class ChunkEvent extends ReanimatorsEvent {
    private Chunk chunk;
    private SPacketChunkData packet;

    public ChunkEvent(Chunk chunk, SPacketChunkData packet) {
        this.chunk = chunk;
        this.packet = packet;
    }

    public Chunk getChunk() {
        return chunk;
    }

    public SPacketChunkData getPacket() {
        return packet;
    }
}
