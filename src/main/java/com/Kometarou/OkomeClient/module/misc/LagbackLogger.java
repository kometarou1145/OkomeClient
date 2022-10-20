package com.Kometarou.OkomeClient.module.misc;

import com.Kometarou.OkomeClient.event.client.PacketEvent;
import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LagbackLogger extends Module {
    public LagbackLogger() {
        super("LagBack", Category.MISC);
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive event) {
        if (nullCheck()) return;

        if (event.getPacket() instanceof SPacketPlayerPosLook) {
            SPacketPlayerPosLook packet = (SPacketPlayerPosLook) event.getPacket();
            sendMessage("xyz : " + packet.x + "," + packet.y + "," + packet.z + "; rotate :" + packet.yaw + " - " + packet.pitch);
        }
    }
}
