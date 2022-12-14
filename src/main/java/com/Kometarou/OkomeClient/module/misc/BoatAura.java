package com.Kometarou.OkomeClient.module.misc;

import com.Kometarou.OkomeClient.manager.RotateManager;
import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;
import com.Kometarou.OkomeClient.module.Setting;
import com.Kometarou.OkomeClient.util.Util;
import com.Kometarou.OkomeClient.util.client.Timer;
import com.Kometarou.OkomeClient.util.player.PlayerUtil;
import com.Kometarou.OkomeClient.util.render.GeometryMasks;
import com.Kometarou.OkomeClient.util.render.RenderUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;

import java.awt.*;
import java.util.Comparator;

public class BoatAura extends Module {
    public static BoatAura INSTANCE;
    private final com.Kometarou.OkomeClient.util.client.Timer timer = new Timer();
    public Setting<Float> delay = register(new Setting("Delay", 30.0f, 100.0f, 1.0f));
    public Setting<Float> range = register(new Setting("Range", 5.0f, 13.0f, 0.1f));
    public Setting<Float> wallRange = register(new Setting("Wall Range", 2.0f, 13.0f, 0.1f));
    public Setting<Boolean> render = register(new Setting("Render", true));
    public Setting<Color> color = register(new Setting("Color", new Color(255, 0, 0, 100)));
    public Entity target = null;

    public BoatAura() {
        super("BoatAura", Category.MISC);
        INSTANCE = this;
    }

    @Override
    public void onTick() {
        if (nullCheck())
            return;

        modifyTarget();

        if (target == null || PlayerUtil.getDistance(target) > range.getValue()) {
            target = Util.mc.world.loadedEntityList
                    .stream().filter(e -> e instanceof EntityBoat).filter(this::rangeCheck).min(Comparator.comparing(PlayerUtil::getDistance)).orElse(null);
        }

        if (timer.passedDms(delay.getValue()) && target != null) {
            RotateManager.lookAtEntity(target);
            Util.mc.player.connection.sendPacket(new CPacketUseEntity(target));
            Util.mc.player.connection.sendPacket(new CPacketAnimation(EnumHand.MAIN_HAND));
            timer.reset();
        }
    }

    @Override
    public void onRender3D() {
        if (target != null && render.getValue()) {
            RenderUtil.drawBox(target.boundingBox, true, 1.0f, color.getValue(), GeometryMasks.Quad.ALL);
        }
    }

    private void modifyTarget() {
        if (target != null) {
            if (!rangeCheck(target)) {
                target = null;
                RotateManager.reset();
                return;
            }

            if (target.isDead) {
                target = null;
                RotateManager.reset();
                return;
            }

            if (target == null) {
                RotateManager.reset();
            }
        }
    }

    private boolean rangeCheck(Entity e) {
        if (PlayerUtil.getDistance(e) > range.getValue())
            return false;
        return PlayerUtil.canSeeEntity(e) || !(PlayerUtil.getDistance(e) > wallRange.getValue());
    }

    @Override
    public void onDisable() {
        RotateManager.reset();
        target = null;
    }
}
