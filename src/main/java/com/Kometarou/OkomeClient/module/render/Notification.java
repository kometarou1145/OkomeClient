package com.Kometarou.OkomeClient.module.render;

import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;
import com.Kometarou.OkomeClient.module.Setting;

public class Notification extends Module {
    public static Notification INSTANCE;

    public Setting<String> mode = register(new Setting("Mode", "Chat", new String[]{"Chat", "Modern"}));
    public Setting<Float> time = register(new Setting("Time", 1.5F, 5.0F, 0.5F));
    public Setting<Float> speed = register(new Setting("Speed", 3.0F, 5.0F, 1.0F));
    public Setting<Boolean> toggleMessage = register(new Setting("Toggle", true));
    public Setting<Boolean> log = register(new Setting("Log", true));

    public Notification() {
        super("Notification", true, Category.RENDER);
        INSTANCE = this;
    }
}
