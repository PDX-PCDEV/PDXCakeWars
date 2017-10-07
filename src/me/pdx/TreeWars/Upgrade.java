package me.pdx.TreeWars;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.pdx.TreeWars.Main;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class Upgrade implements Listener{
    Inventory inv = Bukkit.createInventory(null, 9, "Upgrade");
    //Inventory shop = Bukkit.createInventory(null,9,"Purchase:");
    ItemStack is = new ItemStack(Material.SIGN, 1);
    Block[] blockList;
    @EventHandler
    public void OnPlayerInteract (PlayerInteractEvent e) {
        if (e.getClickedBlock().getType() == Material.WALL_SIGN) {
            int index = 0;
            for (int r = 0; r < blockList.length; r++) {
                if (e.getClickedBlock().equals(blockList[r])) {
                    index = r;
                }
            }
            ItemMeta im = is.getItemMeta();
            im.setDisplayName("Generator:" + index);
            is.setItemMeta(im);
            inv.setItem(4, is);

            Player player = e.getPlayer();
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Inventory clone = inv;

                player.openInventory(clone);
            }
        }
    }

    @EventHandler
    public void OnInventoryClick (InventoryClickEvent e) {
        if (e.getInventory().getName() == "Upgrade") {
            HumanEntity player = e.getWhoClicked();
            if (e.getCurrentItem().getType() == Material.SIGN) {
                e.setCancelled(true);
                ItemMeta em = e.getCurrentItem().getItemMeta();
                int index = Integer.parseInt(em.getDisplayName().split(":")[1]);
                Sign sign = (Sign) blockList[index].getState();
                int level = Integer.parseInt(sign.getLine(1).split(":")[1]);
                if (level > 2) {
                    return;
                }

                if (sign.getLine(0) == "Iron") {
                    player.sendMessage("d");
                    if (level == 1) {
                        if(!checkCost(Material.IRON_INGOT, 10, player)) {return;}
                    }
                } else if (sign.getLine(0) == "Gold") {
                    if (level == 1) {
                        if(!checkCost(Material.IRON_INGOT, 30, player)) {return;}
                    }
                }

                sign.setLine(1, "Level:" + (level + 1));
                sign.update();
                e.getWhoClicked().closeInventory();



                Firework firework = (Firework) player.getLocation().getWorld().spawnEntity(player.getLocation().add(0,-1,0), EntityType.FIREWORK);
                FireworkMeta fm = firework.getFireworkMeta();
                FireworkEffect fe = FireworkEffect.builder().flicker(true).withColor(Color.AQUA, Color.LIME).with(FireworkEffect.Type.BALL_LARGE).trail(false).build();
                fm.addEffect(fe);
                firework.setFireworkMeta(fm);
            }
        }
    }
    public boolean checkCost (Material mat, int number, HumanEntity player) {
        for (int fooey = 0; fooey < player.getInventory().getContents().length; fooey++) {
            if (player.getInventory().getContents()[fooey] != null) {
                if (player.getInventory().getContents()[fooey].getType() == mat && player.getInventory().getContents()[fooey].getAmount() > number) {
                    player.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 10));
                    return true;
                }
            }
        }
        player.sendMessage(ChatColor.RED + "You are " + number + " " + mat.name() + " short!");
        return false;
    }
}
