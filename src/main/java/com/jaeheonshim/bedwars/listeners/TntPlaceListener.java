package com.jaeheonshim.bedwars.listeners;

import com.jaeheonshim.bedwars.BedwarsGameManager;
import com.jaeheonshim.bedwars.domain.BedwarsGame;
import org.bukkit.Material;
import org.bukkit.block.data.type.TNT;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class TntPlaceListener implements Listener {
    @EventHandler
    public void handleTntPlace(BlockPlaceEvent event) {
        BedwarsGameManager manager = BedwarsGameManager.getInstance();
        if(event.getBlock().getType() == Material.TNT && manager.getGameOfPlayer(event.getPlayer().getUniqueId().toString()) != null) {
            event.getBlock().setType(Material.AIR);
            event.getBlock().getLocation().getWorld().spawnEntity(event.getBlock().getLocation(), EntityType.PRIMED_TNT);
        }
    }
}
