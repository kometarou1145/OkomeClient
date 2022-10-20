package com.Kometarou.OkomeClient.module.render;

import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;
import com.Kometarou.OkomeClient.ui.clickgui.AtopiXGui;

public class ClickGui extends Module {
    public ClickGui() {
        super("ClickGui", Category.RENDER);
    }

    @Override
    public void onEnable() {
        if (nullCheck()) {
            disable();
            return;
        }

        if (mc.currentScreen == null)
            mc.displayGuiScreen(new AtopiXGui());
    }

    @Override
    public void onTick() {
        if (!(mc.currentScreen instanceof AtopiXGui))
            disable();
    }
}
