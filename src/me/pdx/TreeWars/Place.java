package me.pdx.TreeWars;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class Place implements Listener {
    Main main;
    @EventHandler
    public void OnBlockPlace (BlockPlaceEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlockPlaced();
        String team = player.getScoreboard().getPlayerTeam(player).getName();
        block.setMetadata("team",new FixedMetadataValue(main,team));
    }
}
