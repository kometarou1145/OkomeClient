package com.Kometarou.OkomeClient.module.render;

import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;
import com.Kometarou.OkomeClient.module.Setting;

public class CameraClip extends Module {
    public static CameraClip INSTANCE;
    public Setting<Boolean> extend = this.register(new Setting("Extend", false));
    public Setting<Float> distance = this.register(new Setting("Distance", 10.0F, 50.0F, 0.0F, v -> this.extend.getValue()));

    public CameraClip() {
        super("CameraClip", Category.RENDER);
        INSTANCE = this;
    }
}
