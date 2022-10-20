package com.Kometarou.OkomeClient.module.misc;

import com.Kometarou.OkomeClient.event.client.PacketEvent;
import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoSwing extends Module {
    public NoSwing() {
        super("NoSwing", Category.MISC);
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send e) {
        if (e.getPacket() instanceof CPacketAnimation) e.setCanceled(true);
    }
}