package com.Kometarou.OkomeClient.module.render;

import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;
import com.Kometarou.OkomeClient.module.Setting;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CustomFov extends Module {
    public Setting<Float> fov = register(new Setting("Fov", 70.0f, 200.0f, 1.0f));

    public CustomFov() {
        super("CustomFov", Category.RENDER);
    }

    @SubscribeEvent
    public void onEntityViewRender(EntityViewRenderEvent.FOVModifier event) {
        event.setFOV(fov.getValue());
    }
}
