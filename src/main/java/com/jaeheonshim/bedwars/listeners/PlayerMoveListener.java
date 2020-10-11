package com.jaeheonshim.bedwars.listeners;

import com.jaeheonshim.bedwars.BedwarsGameManager;
import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {
    @EventHandler
    public void handlePlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        BedwarsPlayer bwplayer = BedwarsGameManager.getInstance().getBedwarsPlayer(player.getUniqueId().toString());

        if(bwplayer != null) {
            bwplayer.setAfk(false);
            bwplayer.resetAfkTimer();
        }
    }
}
