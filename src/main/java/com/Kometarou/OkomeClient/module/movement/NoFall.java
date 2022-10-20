package com.Kometarou.OkomeClient.module.movement;

import com.Kometarou.OkomeClient.event.client.PacketEvent;
import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;
import com.Kometarou.OkomeClient.module.Setting;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoFall extends Module {
    private final int bruh = 0;
    public Setting<Boolean> bypass = register(new Setting("Bypass", true));

    public NoFall() {
        super("NoFall", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        if (nullCheck())
            return;

        if (bypass.getValue()) {
            if (mc.player.fallDistance > 3.5) {
                for (int i = 0; i < 30; i++) {
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY - 255, mc.player.posZ, false));
                }
            }

        }
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send event) {
        if (nullCheck()) return;

        if (!bypass.getValue() && event.getPacket() instanceof CPacketPlayer) {
            CPacketPlayer packet = (CPacketPlayer) event.getPacket();
            packet.onGround = true;
        }
    }
}
