package me.pdx.TreeWars;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.event.entity.EntityDismountEvent;
import org.spigotmc.event.entity.EntityMountEvent;

public class Main extends JavaPlugin {
    World world;
    int teams = 2;
    int count = 0;

    public Block[] blocks = new Block[teams];
    Location[] locales = new Location[teams];

    Material[] wps = {Material.STONE_SWORD, Material.GOLD_SWORD, Material.IRON_SWORD, Material.DIAMOND_SWORD};
    Material[] ams = {Material.GOLD_CHESTPLATE,Material.IRON_CHESTPLATE,Material.DIAMOND_CHESTPLATE};

    public void onEnable () {
        world = Bukkit.getServer().getWorlds().get(0);
        Location[] cakes = {new Location(world,-20f,68f,91f)};
        locales[0] = new Location (world, -21.5f, 68f, 82.5f);
        locales[1] = new Location(world, -23.5f, 68f, 82.5f);

        for (int i = 0; i < teams; i++) {
            blocks[i] =  locales[i].getBlock();
            getLogger().info(locales[i].getBlock().getType().toString());
            getLogger().info(blocks[i].getType().toString());
            ((Sign) blocks[i].getState()).setLine(1, "Level:1");
        }

        Upgrade ug = new Upgrade();
        ug.blockList = blocks;
        Shop sp = new Shop ();
        SetupShop(sp);
        Place pc = new Place();
        pc.main = this;

        Bukkit.getServer().getPluginManager().registerEvents(ug,this);
        Bukkit.getServer().getPluginManager().registerEvents(sp,this);
        Bukkit.getServer().getPluginManager().registerEvents(pc,this);
        Bukkit.getServer().getPluginManager().registerEvents(new Join(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new CakeHandler(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new Death(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new Traps(),this);
        getLogger().info ("Treewars has been enabled.");

        for (int t = 0; t < cakes.length; t++) {
            cakes[t].getBlock().setMetadata("cakeness", new FixedMetadataValue(Bukkit.getPluginManager().getPlugin("TreeWars"),"blue"));
        }

        new MyTask().runTaskTimer(this,0,40);
    }

    public void onDisable () {
        getLogger().info ("Treewars has been disabled.");
    }

    void SetupShop (Shop shop) {
        ItemStack wpS = new ItemStack(Material.DIAMOND_SWORD, 1);
        shop.shop.setItem(2,wpS);
        ItemStack arS = new ItemStack(Material.IRON_CHESTPLATE, 1);
        shop.shop.setItem(3,arS);
        ItemStack bkS = new ItemStack(Material.WOOL, 1);
        shop.shop.setItem(4,bkS);
        ItemStack bwS = new ItemStack(Material.BOW, 1);
        shop.shop.setItem(5,bwS);
        ItemStack mcS = new ItemStack(Material.EGG, 1);
        shop.shop.setItem(6,mcS);

        for (int w = 0; w < wps.length; w++) {
            shop.srdI.setItem(w, new ItemStack(wps[w],1));
        }
        for (int a = 0; a < ams.length; a++) {
            shop.amrI.setItem(a, new ItemStack(ams[a],1));
        }
    }

    public class MyTask extends BukkitRunnable {
        public void run () {
            if (count % 10 == 0) {
                for (int j = 0; j < locales.length; j++) {
                    Sign cos = (Sign) blocks[j].getState();
                    if ((cos.getLine(0).equals("Iron")) && (cos.getLine(1).equals("Level:1"))) {
                        ItemStack stack = new ItemStack(Material.IRON_INGOT);
                        world.dropItem(locales[j], stack);
                    }
                }
            }
            if (count % 5 == 0) {
                for (int j = 0; j < locales.length; j++) {
                    Sign cos = (Sign) blocks[j].getState();
                    if ((cos.getLine(0).equals("Iron")) && (cos.getLine(1).equals("Level:2"))) {
                        ItemStack stack = new ItemStack(Material.IRON_INGOT);
                        world.dropItem(locales[j], stack);
                    }
                }
            }
            if (count % 12 == 0) {
                for (int j = 0; j < locales.length; j++) {
                    Sign cos = (Sign) blocks[j].getState();
                    if ((cos.getLine(0).equals("Gold")) && (cos.getLine(1).equals("Level:1"))) {
                        ItemStack stack = new ItemStack(Material.GOLD_INGOT);
                        world.dropItem(locales[j], stack);
                    }
                }
            }
            if (count % 6 == 0) {
                for (int j = 0; j < locales.length; j++) {
                    Sign cos = (Sign) blocks[j].getState();
                    if ((cos.getLine(0).equals("Gold")) && (cos.getLine(1).equals("Level:2"))) {
                        ItemStack stack = new ItemStack(Material.GOLD_INGOT);
                        world.dropItem(locales[j], stack);
                    }
                }
            }

            count += 1;
            if (count == 80) {
                count = 0;
            }
        }
    }
}
