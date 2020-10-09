package com.jaeheonshim.bedwars.domain;

import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
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
    private ArmorLevel armorLevel = ArmorLevel.LEATHER;

    private boolean isDead;
    private long deathMessageTimer;
    private long respawnTimer = -1;
    private long pvpTagTimer;

    private BedwarsTeam team;

    private String pvpTagUuid;

    public BedwarsPlayer(String uuid, BedwarsTeam team) {
        this.uuid = uuid;
        this.team = team;
    }

    public String getUuid() {
        return uuid;
    }

    public void handleDeath() {
        Player player = Bukkit.getServer().getPlayer(UUID.fromString(uuid));
        player.getInventory().clear();
        player.setFireTicks(0);
        pvpTagUuid = null;

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

        handleTickDeathLogic(delta, player);

        pvpTagTimer -= delta;
        if(pvpTagTimer <= 0) {
            pvpTagUuid = null;
        }
    }

    private void handleTickDeathLogic(long delta, Player player) {
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
    
    public void init() {
        Player player = Bukkit.getServer().getPlayer(UUID.fromString(uuid));
        player.setHealth(20);
        player.getInventory().setArmorContents(getArmor());
        player.getInventory().addItem(new ItemStack(Material.WOODEN_SWORD, 1));
    }

    public void respawn() {
        isDead = false;
        Player player = Bukkit.getServer().getPlayer(UUID.fromString(uuid));
        
        init();
        
        if(player != null) {
            player.teleport(this.team.getRespawnLocation().setDirection(new Vector(0, 0, 1)));
            player.sendMessage(ChatColor.GREEN + "Respawned!");
        }
    }
    
    public ItemStack[] getArmor() {
        switch(armorLevel) {
            case LEATHER:
                return new ItemStack[] {
                        colorLeatherArmor(new ItemStack(Material.LEATHER_BOOTS)),
                        colorLeatherArmor(new ItemStack(Material.LEATHER_LEGGINGS)),
                        colorLeatherArmor(new ItemStack(Material.LEATHER_CHESTPLATE)),
                        colorLeatherArmor(new ItemStack(Material.LEATHER_HELMET))
                };
            default:
                return new ItemStack[] {
                        new ItemStack(Material.valueOf(armorLevel.name() + "_BOOTS")),
                        new ItemStack(Material.valueOf(armorLevel.name() + "_LEGGINGS")),
                        colorLeatherArmor(new ItemStack(Material.LEATHER_CHESTPLATE)),
                        colorLeatherArmor(new ItemStack(Material.LEATHER_HELMET))
                };
        }
    }

    public void setArmorLevel(ArmorLevel armorLevel) {
        this.armorLevel = armorLevel;

        Player player = Bukkit.getServer().getPlayer(UUID.fromString(uuid));
        player.getInventory().setArmorContents(getArmor());
    }

    public ItemStack colorLeatherArmor(ItemStack item) {
        LeatherArmorMeta itemMeta = ((LeatherArmorMeta) item.getItemMeta());

        itemMeta.setColor(team.getTeamColor().getColor());
        item.setItemMeta(itemMeta);

        return item;
    }

    public void tagPvp(String uuid) {
        pvpTagUuid = uuid;
        pvpTagTimer = Duration.ofSeconds(10).toMillis();
    }

    public String getPvpTaggedUuid() {
        return pvpTagUuid;
    }

    public BedwarsTeam getTeam() {
        return team;
    }

    public ArmorLevel getArmorLevel() {
        return armorLevel;
    }
}
