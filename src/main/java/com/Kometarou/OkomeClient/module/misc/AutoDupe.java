package com.Kometarou.OkomeClient.module.misc;

import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;
import com.Kometarou.OkomeClient.util.player.InventoryUtil;
import net.minecraft.block.BlockPlanks;
import net.minecraft.network.play.client.CPacketPlayer;

public class AutoDupe extends Module {
    public AutoDupe() {
        super("AutoDupe", Category.MISC);
    }

    @Override
    public void onEnable() {
        if (nullCheck()) return;

        if (mc.player.getHeldItemMainhand().isEmpty()) {
            sendMessage("You must have item in Mainhand");
            disable();
            return;
        }
        int plank = InventoryUtil.getBlockHotbar(BlockPlanks.class);
        if (plank == -1) {
            sendMessage("Cannot find planks in hotbar");
            disable();
            return;
        }
        if (plank == mc.player.inventory.currentItem) {
            sendMessage("You must have item in Mainhand");
            disable();
            return;
        }
        plank += 36;
        //dupe
        mc.player.connection.sendPacket(new CPacketPlayer.Rotation(0, 90, mc.player.onGround));
        InventoryUtil.moveItemTo(plank, 1);
        InventoryUtil.dropItem(mc.player.inventory.currentItem + 36);
        InventoryUtil.moveItemTo(1, plank);
        disable();
    }
}
