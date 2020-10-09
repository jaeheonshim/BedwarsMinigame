package com.jaeheonshim.bedwars.listeners;

import com.jaeheonshim.bedwars.BedwarsGameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class CraftingListener implements Listener {
    @EventHandler
    public void handleCraftItem(CraftItemEvent event) {
        BedwarsGameManager manager = BedwarsGameManager.getInstance();
        if (manager.getGameOfPlayer(event.getWhoClicked().getUniqueId().toString()) != null) {
            event.setCancelled(true);
        }
    }
}

