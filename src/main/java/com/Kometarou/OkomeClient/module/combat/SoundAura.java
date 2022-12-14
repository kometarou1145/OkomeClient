package com.Kometarou.OkomeClient.module.combat;

import com.Kometarou.OkomeClient.event.player.UpdateWalkingPlayerEvent;
import com.Kometarou.OkomeClient.manager.FriendManager;
import com.Kometarou.OkomeClient.manager.RotateManager;
import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;
import com.Kometarou.OkomeClient.module.Setting;
import com.Kometarou.OkomeClient.util.Util;
import com.Kometarou.OkomeClient.util.client.Timer;
import com.Kometarou.OkomeClient.util.entity.EntityType;
import com.Kometarou.OkomeClient.util.player.PlayerUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.util.Comparator;
import java.util.Objects;

public class SoundAura extends Module {
    public static SoundAura INSTANCE;
    private final Timer timer = new Timer();
    public Setting<Float> delay = register(new Setting("Delay", 0.0f, 0.0f, 0.0f));
    public Setting<Float> range = register(new Setting("Range", 100.0f, 100.0f, 100.0f));
    public Setting<Float> wallRange = register(new Setting("Wall Range", 100.0f, 100.0f, 100.0f));
    public Setting<Boolean> player = register(new Setting("Player", true));
    public Setting<Boolean> monster = register(new Setting("Monster", false));
    public Setting<Boolean> neutral = register(new Setting("Neutral", false));
    public Setting<Boolean> boss = register(new Setting("Boss", false));
    public Setting<Boolean> villager = register(new Setting("Villager", false));
    public Setting<Boolean> render = register(new Setting("Render", true));
    public Setting<Color> color = register(new Setting("Color", new Color(255, 0, 0, 100)));
    public EntityLivingBase target = null;

    public SoundAura() {
        super("SoundAura", Category.COMBAT);
        INSTANCE = this;
    }

    @Override
    public void onTick() {
        if (nullCheck())
            return;

        modifyTarget();

        if (target == null || PlayerUtil.getDistance(target) > range.getValue()) {
            target = (EntityLivingBase) Util.mc.world.loadedEntityList
                    .stream().filter(this::isEntityTarget).filter(this::rangeCheck).min(Comparator.comparing(PlayerUtil::getDistance)).orElse(null);
        }

        if (timer.passedDms(delay.getValue()) && target != null) {
            Util.mc.player.connection.sendPacket(new CPacketUseEntity(target));
            Util.mc.player.connection.sendPacket(new CPacketAnimation(EnumHand.MAIN_HAND));
            timer.reset();
        }
    }

    @SubscribeEvent
    public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent event) {
        if (nullCheck()) return;

        if (target == null) return;
        RotateManager.lookAtEntity(target);
    }

    private void modifyTarget() {
        if (target != null) {
            if (!rangeCheck(target)) {
                target = null;
                RotateManager.reset();
                return;
            }

            if (target.isDead || target.getHealth() < 0.1) {
                target = null;
                RotateManager.reset();
                return;
            }

            if (!player.getValue() && target instanceof EntityPlayer) {
                EntityPlayer f = (EntityPlayer) target;
                if (f.getDisplayNameString().equalsIgnoreCase(Util.mc.player.getDisplayNameString()))
                    target = null;
            }
            if (!monster.getValue() && EntityType.isMonster(target))
                target = null;
            if (!neutral.getValue() && EntityType.isNeutral(target))
                target = null;
            if (!boss.getValue() && EntityType.isBoss(target))
                target = null;
            if (!villager.getValue() && target instanceof EntityVillager)
                target = null;

            if (target == null) {
                RotateManager.reset();
            }
        }
    }

    private boolean isEntityTarget(Entity e) {
        if (!(e instanceof EntityLivingBase) || ((EntityLivingBase) e).getHealth() <= 0) {
            return false;
        }

        if (e.entityId == Util.mc.player.entityId || e.entityId < 0)
            return false;

        if (Objects.equals(e, Util.mc.player.ridingEntity))
            return false;

        if (player.getValue() && e instanceof EntityPlayer && !FriendManager.isFriend((EntityPlayer) e))
            return true;
        if (monster.getValue() && EntityType.isMonster(e))
            return true;
        if (neutral.getValue() && EntityType.isNeutral(e))
            return true;
        if (boss.getValue() && EntityType.isBoss(e))
            return true;
        return villager.getValue() && e instanceof EntityVillager;
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
