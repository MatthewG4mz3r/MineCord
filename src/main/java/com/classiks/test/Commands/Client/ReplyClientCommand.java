package com.classiks.test.Commands.Client;

import com.classiks.test.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ReplyClientCommand implements CommandExecutor {

    private Main main;

    public ReplyClientCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length >= 1) {
                if (main.getRecentMessages().containsValue(player.getUniqueId())) {
                    UUID uuid = main.getRecentMessages().get(player.getUniqueId());

                    if (Bukkit.getPlayer(uuid) != null) {
                        Player target = Bukkit.getPlayer(uuid);

                        StringBuilder msg = new StringBuilder();

                        for (int i = 0; i < args.length; i++) {
                            msg.append(args[0]);
                            msg.append(" ");
                        }

                        player.sendMessage(ChatColor.DARK_PURPLE + player.getDisplayName() + ChatColor.WHITE + " -> " + ChatColor.DARK_AQUA + target.getDisplayName() + " > " + ChatColor.YELLOW + msg.toString());
                        target.sendMessage(ChatColor.DARK_PURPLE + player.getDisplayName() + " > " + ChatColor.YELLOW + msg.toString());
                    } else {
                        player.sendMessage(ChatColor.RED + "The player you are replying to is no longer online");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Nobody online has messaged you yet!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "The rep command require at least 1 argument!\n usage: /rep <msg>");
            }
        } else {
            System.out.println("This is a client only command for the MineCord direct messaging system");
        }

        return false;
    }
}
