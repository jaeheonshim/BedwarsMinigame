package com.jaeheonshim.bedwars.listeners;

import com.jaeheonshim.bedwars.domain.BedwarsGame;
import com.jaeheonshim.bedwars.BedwarsGameManager;
import com.jaeheonshim.bedwars.Util;
import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import com.jaeheonshim.bedwars.domain.BedwarsTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.data.type.Bed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BedBreakListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void handleBedBreak(BlockBreakEvent event) {
        Bukkit.getLogger().info("Block broken of type " + event.getBlock().getType().name());

        if(Util.beds.contains(event.getBlock().getType())) {
            BedwarsGameManager gameManager = BedwarsGameManager.getInstance();
            BedwarsGame game = gameManager.getGameOfPlayer(event.getPlayer().getUniqueId().toString());
            BedwarsPlayer breakPlayer = gameManager.getBedwarsPlayer(event.getPlayer().getUniqueId().toString());
            Location bedLocation = event.getBlock().getLocation();
            Bed blockData = (Bed) event.getBlock().getBlockData();

            if(breakPlayer == null || game == null) {
                return;
            }

            if(blockData.getPart() == Bed.Part.HEAD) {
                bedLocation.subtract(blockData.getFacing().getDirection());
            }

            BedwarsTeam team = game.getTeamOfBed(bedLocation);
            if(breakPlayer.getTeam().equals(team)) {
                event.getPlayer().sendMessage(ChatColor.RED + "You can't break your own bed!");
                event.setCancelled(true);
                return;
            }

            game.broadcastMessage("---------------------------------------" +
                    "\n\n\n" +
                    Util.getChatFromDye(team.getTeamColor()) + team.getTeamColor().name() + " bed" + ChatColor.RESET + ChatColor.GRAY + " was broken by " + ChatColor.RESET + Util.getChatFromDye(breakPlayer.getTeam().getTeamColor()) + event.getPlayer().getName() + ChatColor.RESET +
                    "!\n\n" +
                    "---------------------------------------");
            event.setDropItems(false);
        }
    }
}
