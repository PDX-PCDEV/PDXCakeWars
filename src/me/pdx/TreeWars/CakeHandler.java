package me.pdx.TreeWars;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Cake;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

public class CakeHandler implements Listener {
    Main main;
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getClickedBlock().getType() == Material.CAKE_BLOCK) {
            Block cBlock = e.getClickedBlock();
            Player player = e.getPlayer();
            e.setCancelled(true);
            if (cBlock.hasMetadata("cakeness")) {
                if (!(cBlock.getMetadata("cakeness").equals(player.getScoreboard().getPlayerTeam(player).getName()))) {
                    if (player.getItemInHand().getItemMeta().getDisplayName().equals("Knife")) {
                        Cake cake = (Cake)cBlock.getState().getData();
                        e.getPlayer().sendMessage("foo");
                        cake.setSlicesRemaining((cake.getSlicesRemaining() - 1));
                        cBlock.setData(cake.getData());
                        ItemStack knife = new ItemStack(Material.IRON_SWORD, 1);
                        ItemMeta im = knife.getItemMeta();
                        im.setDisplayName("Knife");
                        knife.setItemMeta(im);
                        player.getInventory().remove(knife);
                    }
                }
            }
        }
    }
}
