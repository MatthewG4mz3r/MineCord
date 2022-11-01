package com.classiks.test;

import com.classiks.test.Commands.Client.MessageClientCommand;
import com.classiks.test.Commands.Client.ReplyClientCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

//      /msg <Player> <Message>
//      /rep <Message>

public final class Main extends JavaPlugin {

    private HashMap<UUID, UUID> recentMessages = new HashMap<>();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new Events(this), this);

        getCommand("msg").setExecutor(new MessageClientCommand(this));
        getCommand("rep").setExecutor(new ReplyClientCommand(this));
    }

    public HashMap<UUID, UUID> getRecentMessages() { return recentMessages; }
}
