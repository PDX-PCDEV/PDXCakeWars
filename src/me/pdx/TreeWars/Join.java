package me.pdx.TreeWars;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {
    @EventHandler
    public void OnPlayerJoin (PlayerJoinEvent e) {
        e.getPlayer().sendMessage("Hi");
    }

    @EventHandler
    public void OnPlayerChat (PlayerChatEvent e) {
        String time = "Time:" + System.currentTimeMillis();
        e.getPlayer().sendMessage(time);
    }
}
