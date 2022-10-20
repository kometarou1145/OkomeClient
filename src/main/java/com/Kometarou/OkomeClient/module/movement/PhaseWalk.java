package com.Kometarou.OkomeClient.module.movement;

import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;
import com.Kometarou.OkomeClient.module.Setting;

public class PhaseWalk extends Module {
    public static PhaseWalk INSTANCE;

    public Setting<Boolean> onlyPhasing = register(new Setting("OnlyPhasing", true));

    public PhaseWalk() {
        super("PhaseWalk", Category.MOVEMENT);

        INSTANCE = this;
    }
}
