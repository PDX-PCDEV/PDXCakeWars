package me.pdx.TreeWars;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Traps implements Listener{
    @EventHandler
    public void OnPlayerInteract (PlayerInteractEvent e) {
        if (e.getAction() == Action.PHYSICAL) {
            Player player = e.getPlayer();
            if (e.getClickedBlock().getType() == Material.WOOD_PLATE) {
                if (e.getClickedBlock().hasMetadata("team")) {
                    player.sendMessage(e.getClickedBlock().getMetadata("team").get(0).asString());
                    if (!e.getClickedBlock().getMetadata("team").get(0).asString().equals(player.getScoreboard().getPlayerTeam(player).getName())) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 300, 1), true);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 300, 1), true);
                        e.getClickedBlock().setType(Material.AIR);
                    }
                }
            }
            if (e.getClickedBlock().getType() == Material.STONE_PLATE) {
                if (e.getClickedBlock().hasMetadata("team")) {
                    player.sendMessage(e.getClickedBlock().getMetadata("team").get(0).asString());
                    if (!e.getClickedBlock().getMetadata("team").get(0).asString().equals(player.getScoreboard().getPlayerTeam(player).getName())) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 300, 1), true);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 300, 1), true);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 300, 1), true);
                        e.getClickedBlock().setType(Material.AIR);
                    }
                }
            }
        }
    }
}
