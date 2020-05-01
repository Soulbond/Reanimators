package com.unco.reanimators.event.events;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import com.unco.reanimators.Reanimators;
import com.unco.reanimators.event.ReanimatorsEvent;
import com.unco.reanimators.util.Wrapper;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.SPacketEntityStatus;

public class TotemPopEvent extends ReanimatorsEvent {

    private Entity entity;

    public TotemPopEvent(Entity entity) {
        super();
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

}