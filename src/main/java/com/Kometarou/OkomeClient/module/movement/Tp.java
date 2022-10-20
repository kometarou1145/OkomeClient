package com.Kometarou.OkomeClient.module.movement;

import com.Kometarou.OkomeClient.event.player.UpdatePlayerEvent;
import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;
import com.Kometarou.OkomeClient.module.Setting;
import com.Kometarou.OkomeClient.util.player.PlayerUtil;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Tp extends Module {
    public Setting<Float> speed = register(new Setting("Speed", 0.0f, 0.0f, 0.0f));
    public Setting<Float> ySpeed = register(new Setting("Y Speed", 10.0f, 10.0f, 10.0f));
    public Setting<Boolean> fastMode = register(new Setting("Fast Mode", true));
    public Setting<Integer> factor = register(new Setting("Factor", 1, 30, 1));
    public Setting<Boolean> antiKick = register(new Setting("Anti Kick", true));
    public Setting<Float> glide = register(new Setting("Glide", 0.1f, 1.0f, 0.1f));

    public Tp() {
        super("Tp", Category.MOVEMENT);
    }

    @SubscribeEvent
    public void onPlayerUpdate(UpdatePlayerEvent event) {
        mc.player.setVelocity(0, 0, 0);

        double[] f = PlayerUtil.directionSpeed(speed.getValue());
        double x1 = f[0];
        double z1 = f[1];
        double offsetX = x1 / factor.getValue();
        double offsetZ = z1 / factor.getValue();

        if (PlayerUtil.isPlayerMoving()) {
            for (int i = 0; i < factor.getValue(); i++) {
                double x = mc.player.posX + offsetX * i;
                double z = mc.player.posZ + offsetZ * i;

                mc.player.connection.sendPacket(new CPacketPlayer.Position(x, mc.player.posY, z, false));

                if (fastMode.getValue())
                    mc.player.setPosition(x, mc.player.posY, z);
            }
        }

        double tx = mc.player.posX + x1;
        double tz = mc.player.posZ + z1;

        double offsetY = ySpeed.getValue();
        double ySpeed = 0;
        if (mc.player.movementInput.jump) {
            mc.player.connection.sendPacket(new CPacketPlayer.Position(tx, mc.player.posY + offsetY, tz, false));
            ySpeed += offsetY;
        }
        if (mc.player.movementInput.sneak) {
            mc.player.connection.sendPacket(new CPacketPlayer.Position(tx, mc.player.posY - offsetY, tz, false));
            ySpeed -= offsetY;
        }
        if (Math.abs(ySpeed) > 0.1)
            mc.player.setPosition(tx, mc.player.posY + ySpeed, tz);

        if (antiKick.getValue()) {
            double power = glide.getValue() * 0.1;
            mc.player.connection.sendPacket(new CPacketPlayer.Position(tx, mc.player.posY - power, tz, false));
        }

        mc.player.connection.sendPacket(new CPacketPlayer.Position(tx, -256, tz, false));
    }
}
