package com.jaeheonshim.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BedwarsPlayer {
    private String uuid;
    private int kills;
    private int finalKills;
    private int bedBreaks;

    public BedwarsPlayer(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void handleDeath() {
        Player player = Bukkit.getServer().getPlayer(UUID.fromString(uuid));
        if(player != null) {
            Bukkit.getLogger().info("Death :(");
            player.teleport(new Location(player.getWorld(), 0, 200, 0));
            player.sendTitle("YOU DIED!", "", 10, 1000, 10);
        }
    }
}
