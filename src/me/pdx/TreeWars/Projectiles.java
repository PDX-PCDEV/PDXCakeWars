package me.pdx.TreeWars;

import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Projectiles implements Listener {
    @EventHandler
    public void OnPlayerInteract (PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR) {
            Player player = e.getPlayer();
            if (player.getItemInHand().getType() == Material.IRON_INGOT) {
                Projectile pro = player.launchProjectile(Fireball.class);
                pro.setVelocity(pro.getVelocity().multiply(0.4));
                pro.setGravity(false);
                pro.setBounce(true);
            }
        }
    }

    @EventHandler
    public void OnProjectileHit (ProjectileHitEvent e) {
        if (e.getHitBlock() != null) {
            
        }
    }
}
