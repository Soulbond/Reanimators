package com.unco.reanimators.event;

import me.zero.alpine.type.Cancellable;
import com.unco.reanimators.util.Wrapper;

public class ReanimatorsEvent extends Cancellable {

    private Era era = Era.PRE;
    private final float partialTicks;

    public ReanimatorsEvent() {
        partialTicks = Wrapper.getMinecraft().getRenderPartialTicks();
    }

    public Era getEra() {
        return era;
    }

    public float getPartialTicks() {
        return partialTicks;
    }

    public enum Era {
        PRE, PERI, POST
    }

}
