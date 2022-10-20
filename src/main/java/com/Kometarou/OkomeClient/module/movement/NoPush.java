package com.Kometarou.OkomeClient.module.movement;

import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;

public class NoPush extends Module {
    public static NoPush INSTANCE;

    public NoPush() {
        super("NoPush", Category.MOVEMENT);
        INSTANCE = this;
    }
}
