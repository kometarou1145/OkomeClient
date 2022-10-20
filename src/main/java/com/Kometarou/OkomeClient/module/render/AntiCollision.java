package com.Kometarou.OkomeClient.module.render;

import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;

public class AntiCollision extends Module {
    public static AntiCollision INSTANCE;

    public AntiCollision() {
        super("AntiCollision", Category.RENDER);
        INSTANCE = this;
    }
}
