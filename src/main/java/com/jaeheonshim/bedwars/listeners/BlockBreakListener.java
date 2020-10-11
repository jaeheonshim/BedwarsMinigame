package com.jaeheonshim.bedwars.listeners;

import com.jaeheonshim.bedwars.BedwarsGameManager;
import com.jaeheonshim.bedwars.domain.BedwarsGame;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void handleBlockBreak(BlockBreakEvent event) {
        BedwarsGame game = BedwarsGameManager.getInstance().getBedwarsGame(event.getBlock().getWorld());
        if(game != null && !game.isBreakable(event.getBlock().getLocation()) && event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You can only break blocks placed by a player!");
        }
    }
}
