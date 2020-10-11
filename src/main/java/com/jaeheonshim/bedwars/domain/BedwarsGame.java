package com.jaeheonshim.bedwars.domain;

import com.jaeheonshim.bedwars.Disposable;
import com.jaeheonshim.bedwars.generator.*;
import com.jaeheonshim.bedwars.shop.BedwarsTeamShop;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.*;

public class BedwarsGame implements Disposable {
    private World world;
    private List<ItemGen> itemGens = new ArrayList<>();
    private List<BedwarsTeam> teams = new ArrayList<>();
    private List<Location> itemShops = new ArrayList<>();
    private List<Location> teamShops = new ArrayList<>();
    private List<LivingEntity> shopVillagers = new ArrayList<>();
    private List<LivingEntity> teamShopVillagers = new ArrayList<>();

    private Set<Location> playerPlace = new HashSet<>();

    private int deathYPos;

    public BedwarsGame() {
        deathYPos = 0;

        world = Bukkit.getServer().getWorlds().get(0);
        itemGens.add(new EmeraldGen(new Location(world, 1, 69, 6), true));
        itemGens.add(new EmeraldGen(new Location(world, -1, 69, -6), true));

        itemGens.add(new DiamondGen(new Location(world, 0, 69, -34)));
        itemGens.add(new DiamondGen(new Location(world, 0, 69, 34)));

        BedwarsTeam blueTeam = new BedwarsTeam(new Location(world, 30, 70, 0), new Location(world, 38, 70, 0), new Location(world, 44, 70, 0), DyeColor.BLUE);
        BedwarsTeam redTeam = new BedwarsTeam(new Location(world, -30, 70, 0), new Location(world, -38, 70, 0), new Location(world, -44, 70, 0), DyeColor.RED);

        blueTeam.addPlayer(new BedwarsPlayer("028fce20-ab39-4bd3-b829-8e027ee6a72b", blueTeam));
        redTeam.addPlayer(new BedwarsPlayer("d663f687-1ad4-42ef-9771-973da4836e7d", redTeam));
        teams.add(blueTeam);
        teams.add(redTeam);

        itemShops.add(new Location(world, 39, 71, 6));
        itemShops.add(new Location(world, -39, 71, 6));

        teamShops.add(new Location(world, 39, 71, -6));
        teamShops.add(new Location(world, -39, 71, -6));

        init();
    }

    public void init() {
        for(Location location : itemShops) {
            LivingEntity villager = ((LivingEntity) location.getWorld().spawnEntity(location.add(0.5, 0, 0.5), EntityType.VILLAGER));
            villager.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 10000, false, false));
            villager.setCollidable(false);
            villager.setCustomName(ChatColor.AQUA + "ITEM SHOP");
            villager.setCustomNameVisible(true);
            shopVillagers.add(villager);
        }

        for(Location location : teamShops) {
            LivingEntity villager = ((LivingEntity) location.getWorld().spawnEntity(location.add(0.5, 0, 0.5), EntityType.VILLAGER));
            villager.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 10000, false, false));
            villager.setCollidable(false);
            villager.setCustomName(ChatColor.AQUA + BedwarsTeamShop.TITLE);
            villager.setCustomNameVisible(true);
            teamShopVillagers.add(villager);
        }
    }

    public void tick(long delta) {
        for(ItemGen gen : itemGens) {
            gen.tick(delta);
        }

        for(BedwarsTeam team : teams) {
            for(BedwarsPlayer player : team.getTeamPlayers().values()) {
                player.tick(delta);
            }
            team.getGenerators().forEach(gen -> gen.tick(delta));
        }
    }

    public BedwarsTeam getTeamOfBed(Location location) {
        BedwarsTeam team = null;
        for(BedwarsTeam eachTeam : teams) {
            if(eachTeam.getBedLocation().equals(location)) {
                team = eachTeam;
                break;
            }
        }

        return team;
    }

    public BedwarsTeam handleBreakBed(BedwarsPlayer breakPlayer, Location location) {
        BedwarsTeam team = getTeamOfBed(location);

        if(team == null) {
            return null;
        }

        if(team.isBedBroken()) {
            return null;
        }

        team.setBedBroken(true);

        for(BedwarsPlayer player : team.getTeamPlayers().values()) {
            Player bukkitPlayer = Bukkit.getServer().getPlayer(UUID.fromString(player.getUuid()));
            if(bukkitPlayer.isOnline()) {
                broadcastSound(Sound.ENTITY_ENDER_DRAGON_GROWL, 10, 1);
                bukkitPlayer.sendTitle(ChatColor.RED + "BED DESTROYED", ChatColor.YELLOW + "You will no longer respawn when you die!", 5, 80, 10);
            }
        }

        return team;
    }

    public List<BedwarsTeam> getTeams() {
        return teams;
    }
    
    public void broadcastMessage(String message) {
        for(BedwarsTeam team : teams) {
            for(BedwarsPlayer player : team.getTeamPlayers().values()) {
                Player playerBukkit = Bukkit.getServer().getPlayer(UUID.fromString(player.getUuid()));
                if(playerBukkit != null) {
                    playerBukkit.sendMessage(message);
                }
            }
        }
    }

    public void broadcastSound(Sound sound, float v, float v1) {
        for(BedwarsTeam team : teams) {
            for(BedwarsPlayer player : team.getTeamPlayers().values()) {
                Player playerBukkit = Bukkit.getServer().getPlayer(UUID.fromString(player.getUuid()));
                if(playerBukkit != null) {
                    playerBukkit.playSound(playerBukkit.getLocation(), sound, v, v1);
                }
            }
        }
    }

    @Override
    public void dispose() {
        for(ItemGen gen : itemGens) {
            gen.dispose();
        }

        for(LivingEntity entity : shopVillagers) {
            entity.remove();
        }

        for(LivingEntity entity : teamShopVillagers) {
            entity.remove();
        }
    }

    public World getWorld() {
        return world;
    }

    public List<LivingEntity> getShopVillagers() {
        return new ArrayList<>(shopVillagers);
    }

    public List<LivingEntity> getTeamShopVillagers() {
        return new ArrayList<>(teamShopVillagers);
    }

    public boolean isBreakable(Location location) {
        return playerPlace.contains(location);
    }

    public void addPlacedBlock(Location location) {
        playerPlace.add(location);
    }

    public int getDeathYPos() {
        return deathYPos;
    }
}
