package com.classiks.test;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {

    private Main mineCord;

    public Events(Main mineCord) {
        this.mineCord = mineCord;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        mineCord.getRecentMessages().remove(e.getPlayer().getUniqueId());
    }
}
