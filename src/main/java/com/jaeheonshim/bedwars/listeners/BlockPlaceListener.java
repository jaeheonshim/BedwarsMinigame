package com.jaeheonshim.bedwars.listeners;

import com.jaeheonshim.bedwars.BedwarsGameManager;
import com.jaeheonshim.bedwars.domain.BedwarsGame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {
    @EventHandler
    public void handleBlockPlace(BlockPlaceEvent event) {
        BedwarsGame game = BedwarsGameManager.getInstance().getBedwarsGame(event.getBlock().getWorld());
        if(game != null) {
            game.addPlacedBlock(event.getBlock().getLocation());
        }
    }
}
