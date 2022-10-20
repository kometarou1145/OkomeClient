package com.Kometarou.OkomeClient;

import com.Kometarou.OkomeClient.manager.*;
import com.kamiskidder.shgr.manager.*;
import com.Kometarou.OkomeClient.ui.mainmenu.GuiCustomMainMenu;
import com.Kometarou.OkomeClient.util.Util;
import com.Kometarou.OkomeClient.util.client.EventUtil;
import com.Kometarou.OkomeClient.util.client.LogUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

@Mod(modid = okome.MOD_ID, name = okome.MOD_NAME, version = okome.MOD_VERSION)
public class okome implements Util {
    public static final String MOD_ID = "okome";
    public static final String MOD_NAME = "okome";
    public static final String MOD_VERSION = "b11";

    public static final String DISPLAY_NAME = "okome";
    public static final ChatFormatting DISPLAY_COLOR = ChatFormatting.LIGHT_PURPLE;

    //Name of directory
    public static final String DIR_NAME = "okome";

    public static ModuleManager moduleManager;
    public static CommandManager commandManager;
    public static FontManager fontManager;
    public static RotateManager rotateManager;
    public static NotificationManager notificationManager;
    public static MainMenuManager mainMenuManager;

    public static Logger logger = LogManager.getLogger(MOD_ID);

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        LogUtil.info("Starting " + MOD_NAME + " ...");
        Display.setTitle(DISPLAY_NAME + " " + MOD_VERSION);
        moduleManager = new ModuleManager();
        commandManager = new CommandManager();
        fontManager = new FontManager();
        rotateManager = new RotateManager();
        notificationManager = new NotificationManager();
        mainMenuManager = new MainMenuManager();
        ConfigManager.load();
        FriendManager.load();
        mainMenuManager.init();
        LogUtil.info(MOD_NAME + " started!");

        EventUtil.register(this);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onGuiOpened(GuiOpenEvent event) {
        if ((event.getGui() instanceof GuiMainMenu || (event.getGui() == null && mc.player == null))
                && MainMenuManager.canDrawMainMenu) {
            event.setGui(new GuiCustomMainMenu());
        }
    }
}
