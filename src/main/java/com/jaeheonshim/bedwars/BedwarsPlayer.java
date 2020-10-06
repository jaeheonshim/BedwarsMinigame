package com.jaeheonshim.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.time.Duration;
import java.util.UUID;

public class BedwarsPlayer {
    private String uuid;
    private int kills;
    private int finalKills;
    private int bedBreaks;

    private boolean isDead;
    private long deathMessageTimer;
    private long respawnTimer = -1;

    private BedwarsTeam team;

    public BedwarsPlayer(String uuid, BedwarsTeam team) {
        this.uuid = uuid;
        this.team = team;
    }

    public String getUuid() {
        return uuid;
    }

    public void handleDeath() {
        Player player = Bukkit.getServer().getPlayer(UUID.fromString(uuid));
        isDead = true;
        if(player != null) {
            player.teleport(new Location(player.getWorld(), 0, 200, 0));
            player.sendTitle(ChatColor.RED + "YOU DIED!", "", 10, 1000, 10);
            if(!this.team.isBedBroken()) {
                respawnTimer = Duration.ofSeconds(5).toMillis();
            }

            deathMessageTimer = Duration.ofSeconds(5).toMillis();
        }
    }

    public void tick(long delta) {
        Player player = Bukkit.getServer().getPlayer(UUID.fromString(uuid));

        if(player == null) {
            return;
        }

        if(isDead) {
            if(!player.getAllowFlight()) {
                player.setAllowFlight(true);
                player.setFlying(true);
            }

            if(!player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 10000, 1, false));
            }

            if(respawnTimer != -1) {
                respawnTimer -= delta;
                player.resetTitle();
                if(respawnTimer <= 0) {
                    respawnTimer = -1;
                    respawn();
                    return;
                } else {
                    player.sendTitle(ChatColor.RED + "YOU DIED!", ChatColor.YELLOW + "You will respawn in " + respawnTimer / 1000, 0, 1000, 0);
                }
            }

            deathMessageTimer -= delta;
            if(deathMessageTimer <= 0) {
                player.resetTitle();
            }
        } else {
            if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                player.removePotionEffect(PotionEffectType.INVISIBILITY);
            }

            if(player.getAllowFlight() && player.getGameMode() == GameMode.SURVIVAL) {
                player.setAllowFlight(false);
                player.setFlying(false);
            }
        }
    }

    public void respawn() {
        isDead = false;
        Player player = Bukkit.getServer().getPlayer(UUID.fromString(uuid));
        if(player != null) {
            player.teleport(this.team.getRespawnLocation().setDirection(new Vector(0, 0, 1)));
            player.sendMessage(ChatColor.GREEN + "Respawned!");
        }
    }
}
