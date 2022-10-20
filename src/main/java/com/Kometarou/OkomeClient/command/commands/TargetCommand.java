package com.Kometarou.OkomeClient.command.commands;

import com.Kometarou.OkomeClient.command.Command;
import com.Kometarou.OkomeClient.module.combat.AutoGaiji;
import com.Kometarou.OkomeClient.util.entity.EntityUtil;
import net.minecraft.entity.player.EntityPlayer;

public class TargetCommand extends Command {
    public TargetCommand() {
        super();
    }

    @Override
    public void execute(String[] args) {
        if (args[0].equalsIgnoreCase("target")) {
            if (args.length != 2) {
                sendUsage();
                return;
            }

            EntityPlayer player = EntityUtil.getPlayerByName(args[1]);
            if (player == null) {
                sendMessage("Player not found!");
                return;
            }

            AutoGaiji.INSTANCE.target = player;
            sendMessage("Target set to " + player.getDisplayNameString());
        }
    }

    @Override
    public String getUsage() {
        return "target <player>";
    }
}
