package com.jaeheonshim.bedwars;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDamageListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void handleEntityDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = ((Player) event.getEntity());
            if (player.getHealth() - event.getDamage() <= 0) {
                event.setCancelled(true);
                handleDeath(player);
            }
        }
    }

    public void handleDeath(Player player) {
        BedwarsPlayer bedwarsPlayer = BedwarsGameManager.getInstance().getBedwarsPlayer(player.getUniqueId().toString());
        if(bedwarsPlayer != null) {
            bedwarsPlayer.handleDeath();
        }
    }
}
