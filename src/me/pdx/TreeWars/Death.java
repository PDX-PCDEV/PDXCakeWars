package me.pdx.TreeWars;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Death implements Listener {
    @EventHandler
    public void OnPlayerDeath (PlayerDeathEvent e) {
        e.getEntity().setGameMode(GameMode.SPECTATOR);
        e.getEntity().setHealth(20);
        e.setDeathMessage(ChatColor.BLUE + e.getEntity().getDisplayName() + ChatColor.RED + "Got Dunked On");
    }

    /*@EventHandler
    public void OnPlayerClick (PlayerInteractEvent e) {
        if (e.getClickedBlock().getType() == Material.WOOD_STAIRS) {
            Entity g = e.getClickedBlock().getLocation().getWorld().spawnEntity(e.getClickedBlock().getLocation(), EntityType.ARROW);
            g.addPassenger(e.getPlayer());
        }
    }*/
}
