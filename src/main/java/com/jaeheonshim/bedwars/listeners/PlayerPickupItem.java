package com.jaeheonshim.bedwars.listeners;

import com.jaeheonshim.bedwars.BedwarsGameManager;
import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerPickupItem implements Listener {
    @EventHandler
    public void handlePlayerPickup(EntityPickupItemEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = ((Player) event.getEntity());
            BedwarsPlayer bwPlayer = BedwarsGameManager.getInstance().getBedwarsPlayer(player.getUniqueId().toString());
            if(bwPlayer != null) {
                if(bwPlayer.isAfk()) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
