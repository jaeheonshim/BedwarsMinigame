package com.jaeheonshim.bedwars.listeners;

import com.jaeheonshim.bedwars.BedwarsGameManager;
import com.jaeheonshim.bedwars.BedwarsPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public class PlayerDamageListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void handleEntityDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = ((Player) event.getEntity());
            BedwarsPlayer bwplayer = BedwarsGameManager.getInstance().getBedwarsPlayer(player.getUniqueId().toString());
            if (bwplayer != null && player.getHealth() - event.getDamage() <= 0) {
                event.setCancelled(true);
                if(bwplayer.getPvpTaggedUuid() != null) {
                    Player killer = Bukkit.getServer().getPlayer(UUID.fromString(bwplayer.getPvpTaggedUuid()));
                    BedwarsGameManager.getInstance().getGameOfPlayer(bwplayer.getUuid()).broadcastMessage(killer == null ? "Unknown Player" : killer.getDisplayName() + " killed " + player.getDisplayName());
                }
                handleDeath(player);
            }
        }
    }

    public void handleDeath(Player player) {
        BedwarsPlayer bedwarsPlayer = BedwarsGameManager.getInstance().getBedwarsPlayer(player.getUniqueId().toString());
        if(bedwarsPlayer != null) {
            bedwarsPlayer.handleDeath();
        }
    }
}
