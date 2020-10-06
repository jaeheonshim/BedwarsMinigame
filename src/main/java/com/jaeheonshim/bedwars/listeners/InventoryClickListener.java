package com.jaeheonshim.bedwars.listeners;

import com.jaeheonshim.bedwars.shop.BedwarsShop;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class InventoryClickListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void handleInventoryClick(InventoryClickEvent event) {
        Player player = ((Player) event.getWhoClicked());
        ItemStack clicked = event.getCurrentItem();
        InventoryView inventoryView = event.getView();

        if(inventoryView.getTitle().equals(BedwarsShop.getTitle())) {
            event.setCancelled(true);
            Bukkit.getLogger().info("Purchasing an item");
            BedwarsShop.purchaseItem(player, clicked);
        }
    }
}
