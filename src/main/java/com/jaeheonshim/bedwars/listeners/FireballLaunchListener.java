package com.jaeheonshim.bedwars.listeners;

import com.jaeheonshim.bedwars.BedwarsGameManager;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class FireballLaunchListener implements Listener {
    @EventHandler
    public void fireballUseHandler(PlayerInteractEvent event) {
        BedwarsGameManager manager = BedwarsGameManager.getInstance();
        if(event.getMaterial() == Material.FIRE_CHARGE && manager.getGameOfPlayer(event.getPlayer().getUniqueId().toString()) != null) {
            event.setCancelled(true);
            event.getPlayer().getInventory().removeItem(new ItemStack(Material.FIRE_CHARGE));
            event.getPlayer().getLocation().getWorld().spawn(event.getPlayer().getLocation(), Fireball.class);
        }
    }
}
