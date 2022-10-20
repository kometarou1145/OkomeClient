package com.Kometarou.OkomeClient.module.misc;

import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;

public class SlowUse extends Module {
    public SlowUse() {
        super("SlowUse", Category.MISC);
    }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        mc.rightClickDelayTimer = 15;

    }

    @Override
    public void onDisable() {
        if (mc.player == null) return;
        mc.rightClickDelayTimer = 10;

    }
}
