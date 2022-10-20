package com.Kometarou.OkomeClient.module.misc;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRichPresence;
import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;
import com.Kometarou.OkomeClient.okome;

import java.util.Objects;

public class DiscordRPC extends Module {
    private Thread _thread = null;

    public DiscordRPC() {
        super("DiscordRPC", Category.MISC);
    }

    @Override
    public void onEnable() {
        club.minnced.discord.rpc.DiscordRPC lib = club.minnced.discord.rpc.DiscordRPC.INSTANCE;
        String applicationId = "994876839812141077";
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        lib.Discord_Initialize(applicationId, handlers, true, "");
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000; // epoch second
        lib.Discord_UpdatePresence(presence);
        presence.largeImageText = "";
        _thread = new Thread(() ->
        {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                presence.details = "Okome Client Community";
                presence.state = "https://discord.gg/SKhdtpsHcM";
                //presence.state = getDetails();
                presence.largeImageKey = "okomeclientnewicon";
                presence.largeImageText = okome.MOD_VERSION;
                presence.partyId = "ae488379-351d-4a4f-ad32-2b9b01c91657";
                presence.partyMax = 1;
                presence.partySize = 1;
                presence.joinSecret = "https://discord.gg/SKhdtpsHcM";
                lib.Discord_UpdatePresence(presence);

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ignored) {
                }
            }
        }, "RPC-Callback-Handler");

        _thread.start();
    }

    @Override
    public void onDisable() {
        club.minnced.discord.rpc.DiscordRPC.INSTANCE.Discord_Shutdown();
        _thread = null;
    }

    public String getDetails() {
        return Objects.isNull(mc.player) ? "MainMenu" : mc.player.getName();
    }

    public String getState() {
        return Objects.isNull(mc.player) ? "" : Objects.isNull(mc.getCurrentServerData()) ? "SinglePlayer" : mc.getCurrentServerData().serverIP;
    }
}

