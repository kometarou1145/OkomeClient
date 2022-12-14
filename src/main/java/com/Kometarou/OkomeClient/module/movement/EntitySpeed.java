package com.Kometarou.OkomeClient.module.movement;

import com.Kometarou.OkomeClient.event.player.PlayerTravelEvent;
import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;
import com.Kometarou.OkomeClient.module.Setting;
import com.Kometarou.OkomeClient.util.player.PlayerUtil;
import net.minecraft.entity.item.EntityBoat;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntitySpeed extends Module {
    public Setting<Float> speed = register(new Setting("Speed", 1.0f, 3.0f, 0.1f));
    public Setting<Float> jump = register(new Setting("Jump", 1.0f, 3.0f, 0.1f));

    public EntitySpeed() {
        super("EntitySpeed", Category.MOVEMENT);
    }

    @SubscribeEvent
    public void onPlayerTravel(PlayerTravelEvent event) {
        if (nullCheck())
            return;

        if (mc.player.isRiding() && !(mc.player.ridingEntity instanceof EntityBoat)) {
            double x = 0;
            double y = mc.player.ridingEntity.motionY;
            double z = 0;

            mc.player.ridingEntity.rotationYaw = mc.player.rotationYaw;
            if (PlayerUtil.isPlayerMoving()) {
                double[] dir = PlayerUtil.directionSpeed(speed.getValue());
                x = dir[0];
                z = dir[1];
            }

            if (mc.player.ridingEntity.onGround && mc.player.movementInput.jump) {
                y = jump.getValue();
            }

            mc.player.ridingEntity.setVelocity(x, y, z);
        }
    }
}
