package com.Kometarou.OkomeClient.module.movement;

import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;
import com.Kometarou.OkomeClient.module.Setting;

public class NoSlow extends Module {
    public static NoSlow INSTANCE;
    public Setting<Boolean> item = register(new Setting("Item", true));

    public NoSlow() {
        super("NoSlow", Category.MOVEMENT);
        INSTANCE = this;
    }
}
