package com.Kometarou.OkomeClient.module.misc;

import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;
import com.Kometarou.OkomeClient.module.Setting;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import org.apache.commons.io.IOUtils;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class FakePlayer extends Module {
    private final String name = "FitMC";
    private final String uuid = "a3be358a-966f-4043-bfab-2acbfe03e051";
    public Setting<Boolean> copyInv = register(new Setting("CopyInv", true));
    private EntityOtherPlayerMP fakePlayer;

    public FakePlayer() {
        super("FakePlayer", Category.MISC);
    }

    public static String getUuid(String name) {
        JsonParser parser = new JsonParser();
        String url = "https://api.mojang.com/users/profiles/minecraft/" + name;
        try {
            String UUIDJson = IOUtils.toString(new URL(url), StandardCharsets.UTF_8);
            if (UUIDJson.isEmpty()) {
                return "invalid name";
            }
            JsonObject UUIDObject = (JsonObject) parser.parse(UUIDJson);
            return FakePlayer.reformatUuid(UUIDObject.get("id").toString());
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    private static String reformatUuid(String uuid) {
        String longUuid = "";
        longUuid = longUuid + uuid.substring(1, 9) + "-";
        longUuid = longUuid + uuid.substring(9, 13) + "-";
        longUuid = longUuid + uuid.substring(13, 17) + "-";
        longUuid = longUuid + uuid.substring(17, 21) + "-";
        longUuid = longUuid + uuid.substring(21, 33);
        return longUuid;
    }

    @Override
    public void onEnable() {
        if (mc.player == null) {
            this.disable();
            return;
        }
        this.fakePlayer = new EntityOtherPlayerMP(mc.world, new GameProfile(UUID.fromString(this.uuid), this.name));
        this.fakePlayer.copyLocationAndAnglesFrom(mc.player);
        this.fakePlayer.rotationYawHead = mc.player.rotationYawHead;
        this.fakePlayer.inventory.copyInventory(mc.player.inventory);
        mc.world.addEntityToWorld(-100, this.fakePlayer);
    }

    @Override
    public void onDisable() {
        if (mc.world != null && mc.player != null) {
            super.onDisable();
            try {
                mc.world.removeEntity(this.fakePlayer);
            } catch (Exception exception) {
            }
        }
    }
}
