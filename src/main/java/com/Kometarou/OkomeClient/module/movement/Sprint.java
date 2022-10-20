package com.Kometarou.OkomeClient.module.movement;

import com.Kometarou.OkomeClient.event.player.UpdatePlayerEvent;
import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", Category.MOVEMENT);
    }

    @SubscribeEvent
    public void onPlayerUpdate(UpdatePlayerEvent event) {
        mc.player.setSprinting(true);
    }
}
