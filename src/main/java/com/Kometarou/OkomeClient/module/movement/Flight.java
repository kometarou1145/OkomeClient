package com.Kometarou.OkomeClient.module.movement;

import com.Kometarou.OkomeClient.event.player.UpdateWalkingPlayerEvent;
import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;
import com.Kometarou.OkomeClient.module.Setting;
import com.Kometarou.OkomeClient.util.player.PlayerUtil;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Flight extends Module {
    public Setting<Float> speed = register(new Setting("Speed", 3.0F, 10.0F, 0.1F));
    public Setting<Float> ySpeed = register(new Setting("Y Speed", 3.0F, 10.0F, 0.1F));
    public Setting<Float> glide = register(new Setting("Glide", 0.0F, 3.0F, 0.0F));

    public Flight() {
        super("Flight", Category.MOVEMENT);
    }

    @SubscribeEvent
    public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent event) {
        mc.player.setVelocity(0, 0, 0);
        if (PlayerUtil.isPlayerMoving()) {
            double[] motion = PlayerUtil.directionSpeed(speed.getValue());
            mc.player.motionX = motion[0];
            mc.player.motionZ = motion[1];
        }

        mc.player.motionY = glide.getValue() * -1;
        if (mc.player.movementInput.jump) {
            mc.player.motionY = ySpeed.getValue();
        }
        if (mc.player.movementInput.sneak) {
            mc.player.motionY = ySpeed.getValue() * -1;
        }
    }
}
