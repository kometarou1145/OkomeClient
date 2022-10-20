package com.Kometarou.OkomeClient.ui.hud.component;

import com.Kometarou.OkomeClient.manager.FontManager;
import com.Kometarou.OkomeClient.ui.hud.Hud;
import com.Kometarou.OkomeClient.util.render.ColorUtil;
import net.minecraft.client.network.NetworkPlayerInfo;

public class PingHud extends Hud {
    public PingHud() {
        super("Ping");
    }

    @Override
    public void onRenderHud() {
        NetworkPlayerInfo info = mc.getConnection().getPlayerInfo(mc.player.getUniqueID());
        String str = info.getResponseTime() + "ms";
        FontManager.notifMsgFont.drawStringWithShadow(str, getXPos(), getYPos(), ColorUtil.toRGBA(255, 255, 255));
        width = FontManager.notifMsgFont.getStringWidth("Show your response time here");
        height = FontManager.notifMsgFont.getHeight();
    }
}
