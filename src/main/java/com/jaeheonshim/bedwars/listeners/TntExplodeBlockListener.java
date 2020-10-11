package com.jaeheonshim.bedwars.listeners;

import com.jaeheonshim.bedwars.BedwarsGameManager;
import com.jaeheonshim.bedwars.domain.BedwarsGame;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.ArrayList;
import java.util.List;

public class TntExplodeBlockListener implements Listener {
    @EventHandler
    public void handleTntExplode(EntityExplodeEvent event) {
        List<Block> affectedBlocks = new ArrayList<>(event.blockList());

        BedwarsGame game = BedwarsGameManager.getInstance().getBedwarsGame(event.getEntity().getWorld());
        for(Block block : affectedBlocks) {
            if(game != null && !game.isBreakable(block.getLocation())) {
                event.blockList().remove(block);
            }
        }
    }
}
