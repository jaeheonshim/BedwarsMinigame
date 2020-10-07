package com.jaeheonshim.bedwars.listeners;

import com.jaeheonshim.bedwars.BedwarsGame;
import com.jaeheonshim.bedwars.BedwarsGameManager;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void handleEntityDamage(EntityDamageEvent event) {
        BedwarsGameManager manager = BedwarsGameManager.getInstance();
        if(manager.getBedwarsGame(event.getEntity().getLocation().getWorld()) != null) {
            if(event.getEntity().getType() == EntityType.VILLAGER) {
                event.setCancelled(true);
            }
        }
    }
}
