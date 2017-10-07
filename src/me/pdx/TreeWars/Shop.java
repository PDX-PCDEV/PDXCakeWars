package me.pdx.TreeWars;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Shop implements Listener {
    Inventory shop = Bukkit.createInventory(null,9,"Purchase:");
    Inventory srdI = Bukkit.createInventory(null,9,"Purchase Swords:");
    Inventory amrI = Bukkit.createInventory(null,9,"Purchase Armor:");
    Inventory blkI = Bukkit.createInventory(null,9,"Purchase Blocks:");
    Inventory rngI = Bukkit.createInventory(null,9,"Purchase Ranged:");
    Inventory mscI = Bukkit.createInventory(null,9,"Purchase Miscellaneous:");

    @EventHandler
    public void OnPlayerInteractEntity (PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType() == EntityType.VILLAGER) {
            e.setCancelled(true);
            Inventory copy = shop;
            e.getPlayer().openInventory(copy);
        }
    }

    @EventHandler
    public void OnInventoryClick (InventoryClickEvent e) {
        if (e.getInventory().getName() == "Purchase:") {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().openInventory(srdI);
            }
        }
    }
}
