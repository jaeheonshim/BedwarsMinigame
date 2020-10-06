package com.jaeheonshim.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.type.Bed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BedBreakListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void handleBedBreak(BlockBreakEvent event) {
        Bukkit.getLogger().info("Block broken of type " + event.getBlock().getType().name());

        if(Util.beds.contains(event.getBlock().getType())) {
            BedwarsGameManager gameManager = BedwarsGameManager.getInstance();
            BedwarsGame game = gameManager.getGameOfPlayer(event.getPlayer().getUniqueId().toString());
            Location bedLocation = event.getBlock().getLocation();
            Bed blockData = (Bed) event.getBlock().getBlockData();
            if(blockData.getPart() == Bed.Part.HEAD) {
                bedLocation.subtract(blockData.getFacing().getDirection());
            }
            System.out.println(event.getPlayer().getUniqueId().toString());
            gameManager.getGameOfPlayer(event.getPlayer().getUniqueId().toString()).handleBreakBed(bedLocation);
            event.setDropItems(false);
        }
    }
}