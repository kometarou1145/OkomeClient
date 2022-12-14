package com.Kometarou.OkomeClient.command.commands;

import com.Kometarou.OkomeClient.command.Command;
import com.Kometarou.OkomeClient.manager.ModuleManager;
import com.Kometarou.OkomeClient.module.Module;
import org.lwjgl.input.Keyboard;

public class BindCommand extends Command {
    public BindCommand() {
        super();
    }

    @Override
    public void execute(String[] args) {
        if (args[0].equalsIgnoreCase("bind")) {
            if (args.length != 3) {
                sendUsage();
                return;
            }
            Module module = ModuleManager.INSTANCE.getModuleByName(args[1]);
            if (module == null) {
                sendErrorMessage("Cannot find module \"" + args[1] + "\"");
                return;
            }
            int key = Keyboard.getKeyIndex(args[2].toUpperCase());
            if (key == Keyboard.KEY_NONE) {
                sendErrorMessage("Unknown key \"" + args[2] + "\"");
                return;
            }
            module.setBind(key);
            sendMessage("Set bind of " + module.getName() + " to " + args[2].toUpperCase());
        }
    }

    @Override
    public String getUsage() {
        return "bind <module> <key>";
    }
}
