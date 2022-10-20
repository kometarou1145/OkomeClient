package com.Kometarou.OkomeClient.module.misc;

import com.Kometarou.OkomeClient.event.client.PacketEvent;
import com.Kometarou.OkomeClient.module.Module;
import com.Kometarou.OkomeClient.module.Setting;
import com.Kometarou.OkomeClient.module.Category;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TimeChanger extends Module {
    public Setting<Integer> time = register(new Setting("Time", 0, 30000, 0));

    public TimeChanger() {
        super("TimeChanger", Category.MISC);
    }

    @Override
    public void onTick() {
        if (nullCheck())
            return;

        mc.world.setWorldTime(time.getValue());
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketTimeUpdate) {
            SPacketTimeUpdate packet = (SPacketTimeUpdate) event.getPacket();
            packet.worldTime = time.getValue();
            packet.totalWorldTime = time.getValue();
        }
    }
}
