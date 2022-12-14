package com.Kometarou.OkomeClient.command.commands;

import com.Kometarou.OkomeClient.command.Command;
import com.Kometarou.OkomeClient.manager.FriendManager;

import java.util.ArrayList;

public class FriendCommand extends Command {
    public FriendCommand() {
        super();
    }

    @Override
    public void execute(String[] args) {
        if (args[0].equalsIgnoreCase("friend")) {
            if (args.length < 2) {
                sendUsage();
                return;
            }

            String operation = args[1];
            if (operation.equalsIgnoreCase("add")) {
                if (args.length != 3) {
                    sendUsage();
                    return;
                }
                String player = args[2];

                if (FriendManager.isFriend(player)) {
                    sendErrorMessage(String.format("You already added %s to friend!", player));
                } else {
                    FriendManager.addFriend(player);
                    sendMessage(String.format("Added %s to friend!", player));
                }
            } else if (operation.equalsIgnoreCase("del")) {
                if (args.length != 3) {
                    sendUsage();
                    return;
                }
                String player = args[2];

                if (!FriendManager.isFriend(player)) {
                    sendErrorMessage(String.format("%s is not friend!", player));
                } else {
                    FriendManager.removeFriend(player);
                    sendMessage(String.format("Deleted %s from friend!", player));
                }
            } else if (operation.equalsIgnoreCase("list")) {
                ArrayList<String> friends = new ArrayList<>(FriendManager.getFriends());
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < friends.size(); i++) {
                    String ap = friends.get(i);
                    if (i + 1 != friends.size()) {
                        ap += ", ";
                    }
                    builder.append(ap);
                }
                sendMessage(builder.toString());
            } else {
                sendUsage();
            }
        }
    }

    @Override
    public String getUsage() {
        return "friend <add/del/list> <player>";
    }
}
