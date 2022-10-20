package com.Kometarou.OkomeClient.ui.hud.component;

import com.Kometarou.OkomeClient.manager.FontManager;
import com.Kometarou.OkomeClient.ui.hud.Hud;
import com.Kometarou.OkomeClient.util.render.ColorUtil;
import com.kamiskidder.shgr.*;

public class WaterMark2 extends Hud {
    public WaterMark2() {
        super("WaterMark2");
    }

    @Override
    public void onRenderHud() {
        FontManager.notifMsgFont.drawStringWithShadow("OkomeClientNewIcon.png", getXPos(), getYPos(), ColorUtil.toRGBA(168, 0, 192));
        width = FontManager.notifMsgFont.getStringWidth("OkomeClientNewIcon.png");
        height = FontManager.notifMsgFont.getHeight();
    }
}
