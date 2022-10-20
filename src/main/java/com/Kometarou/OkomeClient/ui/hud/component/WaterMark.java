package com.Kometarou.OkomeClient.ui.hud.component;

import com.Kometarou.OkomeClient.manager.FontManager;
import com.Kometarou.OkomeClient.ui.hud.Hud;
import com.Kometarou.OkomeClient.util.render.ColorUtil;

public class WaterMark extends Hud {
    public WaterMark() {
        super("WaterMark");
    }

    @Override
    public void onRenderHud() {
        FontManager.notifMsgFont.drawStringWithShadow("okome client", getXPos(), getYPos(), ColorUtil.toRGBA(168, 0, 192));
        width = FontManager.notifMsgFont.getStringWidth("okome client");
        height = FontManager.notifMsgFont.getHeight();
    }
}
