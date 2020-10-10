package com.jaeheonshim.bedwars.listeners;

import com.jaeheonshim.bedwars.BedwarsGameManager;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void handleEntityDamage(EntityDamageByEntityEvent event) {
        BedwarsGameManager manager = BedwarsGameManager.getInstance();
        if(manager.getBedwarsGame(event.getEntity().getLocation().getWorld()) != null) {
            if(event.getEntity().getType() == EntityType.VILLAGER) {
                if(event.getDamager() instanceof Player && ((Player) event.getDamager()).getGameMode() == GameMode.CREATIVE) {
                    return;
                }
                event.setCancelled(true);
            }
        }
    }
}
