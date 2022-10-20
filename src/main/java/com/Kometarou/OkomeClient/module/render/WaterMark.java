package com.Kometarou.OkomeClient.module.render;

import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;

public class WaterMark extends Module {
    public static WaterMark INSTANCE;

    public WaterMark() {
        super("WaterMark", Category.RENDER);
        INSTANCE = this;
    }
}
