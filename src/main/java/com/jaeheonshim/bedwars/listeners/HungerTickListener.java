package com.jaeheonshim.bedwars.listeners;

import com.jaeheonshim.bedwars.BedwarsGameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class HungerTickListener implements Listener {
    @EventHandler
    public void onHungerTick(FoodLevelChangeEvent event) {
        if (BedwarsGameManager.getInstance().getGameOfPlayer(event.getEntity().getUniqueId().toString()) != null) {
            event.setCancelled(true);
            ((Player) event.getEntity()).setFoodLevel(20);
        }
    }
}
