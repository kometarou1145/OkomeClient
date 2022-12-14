package com.Kometarou.OkomeClient.ui.clickgui;

import com.Kometarou.OkomeClient.manager.ModuleManager;
import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.ui.clickgui.component.ModuleButton;
import com.Kometarou.OkomeClient.util.render.RenderUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Panel extends Component {
    private final List<ModuleButton> buttons = new ArrayList<>();
    public Category category;
    private boolean dragging = false;
    private int diffX, diffY = 0;

    public Panel(Category category, int x, int y) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = 140;
        this.height = 20;

        ModuleManager.INSTANCE.getModulesByCategory(category).forEach(m -> buttons.add(new ModuleButton(m)));
    }

    public void renderPanel(int mouseX, int mouseY) {
        if (dragging) {
            this.x = mouseX + diffX;
            this.y = mouseY + diffY;
        }

        RenderUtil.drawRect(x, y, x + width, y + height, new Color(20, 20, 20));
        drawString(getName(category.name()), x + 4, (int) getCenter(y, height, getStringHeight()), new Color(230, 230, 230), false);

        int finallyHeight = height;
        for (ModuleButton button : buttons) {
            finallyHeight += button.render(x, y + finallyHeight, mouseX, mouseY);
        }

        float w = 1F;
        RenderUtil.drawRect(x, y - w, x + width, y, category.getColor());
        RenderUtil.drawRect(x, y + finallyHeight, x + width, y + finallyHeight + w, category.getColor());
        RenderUtil.drawRect(x - w, y - w, x, y + finallyHeight + w, category.getColor());
        RenderUtil.drawRect(x + width, y - w, x + width + w, y + finallyHeight + w, category.getColor());
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton, boolean clicked) {
        boolean c = false;
        if (isMouseHovering(mouseX, mouseY, x, y, x + width, y + height) && !clicked) {
            AtopiXGui.INSTANCE.bringToFront(this);
            dragging = true;
            diffX = this.x - mouseX;
            diffY = this.y - mouseY;
            c = true;
        }

        execute(b -> b.mouseClicked(mouseX, mouseY, mouseButton));

        return c;
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        dragging = false;
        execute(b -> b.mouseReleased(mouseX, mouseY, state));
    }

    @Override
    public void keyTyped(char typed, int key) {
        execute(p -> p.keyTyped(typed, key));
    }

    private void execute(Consumer<? super ModuleButton> action) {
        buttons.forEach(action);
    }
}
