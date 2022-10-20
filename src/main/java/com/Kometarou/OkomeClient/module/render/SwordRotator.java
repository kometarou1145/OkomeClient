package com.Kometarou.OkomeClient.module.render;

import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;
import com.Kometarou.OkomeClient.module.Setting;

public class SwordRotator extends Module {
    public static SwordRotator INSTANCE;
    public Setting<Float> speed = register(new Setting("Speed", 3.0f, 10.0f, 0.1f));
    public Setting<String> type = register(new Setting("Type", "X", new String[]{"X", "Y", "Z"}));
    public boolean isPressed = false;

    public SwordRotator() {
        super("SwordRotator", Category.RENDER);
        INSTANCE = this;
    }

    @Override
    public void onTick() {
        if (nullCheck()) return;

        isPressed = mc.gameSettings.keyBindAttack.pressed || mc.player.isSwingInProgress;
    }
}
