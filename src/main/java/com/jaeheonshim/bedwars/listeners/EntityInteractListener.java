package com.jaeheonshim.bedwars.listeners;

import com.jaeheonshim.bedwars.BedwarsGame;
import com.jaeheonshim.bedwars.BedwarsGameManager;
import com.jaeheonshim.bedwars.shop.BedwarsShop;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EntityInteractListener implements Listener {
    @EventHandler
    public void handleEntityInteract(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        BedwarsGameManager manager = BedwarsGameManager.getInstance();
        if(entity instanceof LivingEntity) {
            LivingEntity livingEntity = ((LivingEntity) entity);
            BedwarsGame game;
            if((game = manager.getBedwarsGame(livingEntity.getLocation().getWorld())) != null) {
                if(game.getShopVillagers().contains(livingEntity)) {
                    event.setCancelled(true);
                    event.getPlayer().openInventory(BedwarsShop.getInventory(event.getPlayer()));
                }
            }
        }
    }
}
