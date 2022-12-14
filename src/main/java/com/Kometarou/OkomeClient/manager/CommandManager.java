package com.Kometarou.OkomeClient.manager;

import com.Kometarou.OkomeClient.command.Command;
import com.Kometarou.OkomeClient.command.commands.BindCommand;
import com.Kometarou.OkomeClient.command.commands.FriendCommand;
import com.Kometarou.OkomeClient.command.commands.TargetCommand;
import com.Kometarou.OkomeClient.util.client.EventUtil;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    public static String prefix = "=";
    public List<Command> commands = new ArrayList<Command>();

    public CommandManager() {
        commands.add(new BindCommand());
        commands.add(new FriendCommand());
        commands.add(new TargetCommand());

        EventUtil.register(this);
    }

    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
        String msg = event.getMessage();
        if (!msg.isEmpty() && msg.startsWith(prefix)) {
            String[] args = msg.substring(1).split(" ");
            if (args.length == 0) return;
            commands.forEach(c -> c.execute(args));

            Minecraft.getMinecraft().ingameGUI.getChatGUI().addToSentMessages(msg);
            event.setCanceled(true);
        }
    }
}
