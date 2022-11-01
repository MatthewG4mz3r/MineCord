package com.classiks.test.Commands.Client;

import com.classiks.test.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageClientCommand implements CommandExecutor {

    private Main main;

    public MessageClientCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length >= 2) {
                if (Bukkit.getPlayerExact(args[0]) != null) {
                    Player target = Bukkit.getPlayerExact(args[0]);

                    StringBuilder msg = new StringBuilder();

                    for (int i = 1; i < args.length; i++) {
                        msg.append(args[i]);
                        msg.append(" ");
                    }

                    player.sendMessage(ChatColor.DARK_PURPLE + player.getDisplayName() + ChatColor.WHITE + " -> " + ChatColor.DARK_AQUA + target.getDisplayName() + " > " + ChatColor.YELLOW + msg.toString());
                    target.sendMessage(ChatColor.DARK_PURPLE + player.getDisplayName() + " > " + ChatColor.YELLOW + msg.toString());

                    main.getRecentMessages().put(player.getUniqueId(), target.getUniqueId());
                } else {
                    player.sendMessage(ChatColor.RED + "The player you are trying to message does not exist!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "The msg command require at least 2 arguments!\n usage: /msg <player> <msg>");
            }
        } else {
            System.out.println("This is a client only command for the MineCord direct messaging system!");
        }

        return false;
    }
}
