package com.Kometarou.OkomeClient.command;

import com.Kometarou.OkomeClient.manager.CommandManager;
import com.Kometarou.OkomeClient.okome;
import com.Kometarou.OkomeClient.util.other.Base;
import com.mojang.realmsclient.gui.ChatFormatting;

public class Command extends Base {

    public Command() {
    }

    public void execute(String[] args) {
    }

    public String getUsage() {
        return "";
    }

    public void sendUsage() {
        super.sendMessage("[" + okome.DISPLAY_COLOR + okome.DISPLAY_NAME + ChatFormatting.RESET + "] Usage : " + ChatFormatting.GRAY + CommandManager.prefix + getUsage());
    }

    @Override
    public void sendMessage(String msg) {
        super.sendMessage("[" + okome.DISPLAY_COLOR + okome.DISPLAY_NAME + ChatFormatting.RESET + "] " + ChatFormatting.GRAY + msg);
    }

    public void sendErrorMessage(String msg) {
        super.sendMessage("[" + okome.DISPLAY_COLOR + okome.DISPLAY_NAME + ChatFormatting.RESET + "] <" + ChatFormatting.RED + "ERROR" + ChatFormatting.RESET + "> " + ChatFormatting.GRAY + msg);
    }
}
