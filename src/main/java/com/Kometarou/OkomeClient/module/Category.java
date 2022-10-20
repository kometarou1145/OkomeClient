package com.Kometarou.OkomeClient.module;

import java.awt.*;

public enum Category {
    COMBAT(new Color(168, 0, 192)),
    EXPLOIT(new Color(168, 0, 192)),
    MOVEMENT(new Color(168, 0, 192)),
    MISC(new Color(168, 0, 192)),
    RENDER(new Color(168, 0, 192)),
    HUD(new Color(168, 0, 192));


    Color color;

    Category(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }
}
