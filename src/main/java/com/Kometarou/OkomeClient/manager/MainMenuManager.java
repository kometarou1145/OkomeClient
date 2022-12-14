package com.Kometarou.OkomeClient.manager;

import com.Kometarou.OkomeClient.util.client.LogUtil;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MainMenuManager {
    public static boolean canDrawMainMenu = true;
    public static String mainMenuFolder = ConfigManager.folder + "mainmenu/";
    public static String splashScreenFolder = ConfigManager.folder + "splash/";

    public void init() {
        File dir = new File(mainMenuFolder);
        dir.mkdirs();

        LogUtil.info("Downloading assets from github...");
        downloadMainMenuAssets();
        LogUtil.info("Loaded MainMenu!");
    }

    private void downloadMainMenuAssets() {
        downloadFile("background.png");
        downloadFile("cape1.png");
        downloadFile("cape2.png");
        downloadFile("cape3.png");
        downloadFile("elainaBase.png");
        downloadFile("eye.png");
        downloadFile("fish1.png");
        downloadFile("fish2.png");
        downloadFile("fish3.png");
        downloadFile("fish4.png");
        downloadFile("flower1.png");
        downloadFile("flower2.png");
        downloadFile("flower3.png");
        downloadFile("flower4.png");
        downloadFile("flower5.png");
        downloadFile("flower6.png");
        downloadFile("flower7.png");
        downloadFile("flower8.png");
        downloadFile("flower9.png");
        downloadFile("hear1.png");
        downloadFile("hear10.png");
        downloadFile("hear2.png");
        downloadFile("hear3.png");
        downloadFile("hear4.png");
        downloadFile("hear5.png");
        downloadFile("hear6.png");
        downloadFile("hear7.png");
        downloadFile("hear8.png");
        downloadFile("hear9.png");
        downloadFile("mountain.png");
        downloadFile("pedal1.png");
        downloadFile("pedal2.png");
    }

    private void downloadFile(String file) {
        if (!Files.exists(Paths.get(mainMenuFolder + file))) {
            try {
                URL fetchWebsite = new URL("https://raw.githubusercontent.com/AzulineTeam/MainMenu/main/" + file);
                Path f = Files.createFile(Paths.get(mainMenuFolder + file));
                try (InputStream inputStream = fetchWebsite.openStream()) {
                    Files.copy(inputStream, Paths.get(mainMenuFolder + file), StandardCopyOption.REPLACE_EXISTING);
                    LogUtil.info("Downloaded " + file);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                canDrawMainMenu = false;
            }
        }
    }

}
