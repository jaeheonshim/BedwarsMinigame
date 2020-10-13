package com.jaeheonshim.bedwars.listeners;

import com.jaeheonshim.bedwars.BedwarsGameManager;
import com.jaeheonshim.bedwars.domain.BedwarsGame;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class MobSpawnListener implements Listener {
    @EventHandler
    public void handleMobSpawn(EntitySpawnEvent event) {
        BedwarsGame game = BedwarsGameManager.getInstance().getBedwarsGame(event.getLocation().getWorld());
        if(game != null && event.getEntityType() != EntityType.PLAYER) {
            event.setCancelled(true);
        }
    }
}
