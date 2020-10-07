package com.jaeheonshim.bedwars.listeners;

import com.jaeheonshim.bedwars.BedwarsGameManager;
import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import com.jaeheonshim.bedwars.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.UUID;

public class PlayerDamageListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void handleEntityDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = ((Player) event.getEntity());
            BedwarsGameManager instance = BedwarsGameManager.getInstance();
            BedwarsPlayer bwplayer = instance.getBedwarsPlayer(player.getUniqueId().toString());

            if (bwplayer != null && player.getHealth() - event.getDamage() <= 0) {
                event.setCancelled(true);
                if(bwplayer.getPvpTaggedUuid() != null) {
                    Player killer = Bukkit.getServer().getPlayer(UUID.fromString(bwplayer.getPvpTaggedUuid()));
                    killer.playSound(killer.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 10, 4);
                    instance.getGameOfPlayer(bwplayer.getUuid())
                            .broadcastMessage(killer == null ? "Unknown Player" : Util.getChatFromDye(instance.getBedwarsPlayer(killer.getUniqueId().toString()).getTeam().getTeamColor()) + killer.getDisplayName() + ChatColor.GRAY + " killed " + Util.getChatFromDye(bwplayer.getTeam().getTeamColor()) + player.getDisplayName());
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
