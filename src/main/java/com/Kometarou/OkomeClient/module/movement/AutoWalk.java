package com.Kometarou.OkomeClient.module.movement;

import com.Kometarou.OkomeClient.event.player.UpdatePlayerMoveStateEvent;
import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoWalk extends Module {
    public AutoWalk() {
        super("AutoWalk", Category.MOVEMENT);
    }

    @SubscribeEvent
    public void onUpdatePlayerMoveState(UpdatePlayerMoveStateEvent event) {
        mc.player.movementInput.moveForward++;
    }
}
