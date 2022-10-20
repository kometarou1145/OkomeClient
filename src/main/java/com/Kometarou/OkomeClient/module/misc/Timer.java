package com.Kometarou.OkomeClient.module.misc;

import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;
import com.Kometarou.OkomeClient.module.Setting;

public class Timer extends Module {
    public Setting<Float> timer = register(new Setting("Timer", 1.0f, 10.0f, 0.1f));

    public Timer() {
        super("Timer", Category.MISC);
    }

    @Override
    public void onTick() {
        mc.timer.tickLength = 50 / timer.getValue();
    }

    @Override
    public void onDisable() {
        mc.timer.tickLength = 50;
    }
}
