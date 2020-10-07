package com.jaeheonshim.bedwars.listeners;

import com.jaeheonshim.bedwars.BedwarsGameManager;
import com.jaeheonshim.bedwars.BedwarsPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PvpListener implements Listener {
    @EventHandler
    public void handlePvp(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player damager = ((Player) event.getDamager());
            Player damaged = ((Player) event.getEntity());

            BedwarsPlayer damagerPlayer = BedwarsGameManager.getInstance().getBedwarsPlayer(damager.getUniqueId().toString());
            BedwarsPlayer damagedPlayer = BedwarsGameManager.getInstance().getBedwarsPlayer(damaged.getUniqueId().toString());

            if(damagerPlayer.getTeam().equals(damagedPlayer.getTeam())) {
                event.setCancelled(true);
                return;
            }

            if(damagerPlayer != null && damagedPlayer != null) {
                damagedPlayer.tagPvp(damagerPlayer.getUuid());
            }
        }
    }
}
