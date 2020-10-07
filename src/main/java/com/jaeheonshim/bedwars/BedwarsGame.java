package com.jaeheonshim.bedwars;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class BedwarsGame implements Disposable {
    private World world;
    private List<ItemGen> itemGens = new ArrayList<>();
    private List<BedwarsTeam> teams = new ArrayList<>();
    private List<Location> itemShops = new ArrayList<>();
    private List<LivingEntity> shopVillagers = new ArrayList<>();

    public BedwarsGame() {
        world = Bukkit.getServer().getWorlds().get(0);
        itemGens.add(new EmeraldGen(new Location(world, 0, 100.5, 0)));
        itemGens.add(new EmeraldGen(new Location(world, -19, 100.6, 0)));
        itemGens.add(new IronGen(new Location(world, -14, 101, 26)));
        BedwarsTeam sampleTeam = new BedwarsTeam(new Location(world, -14, 101, 20), new Location(Bukkit.getServer().getWorlds().get(0), -14, 102, 24), Material.BLUE_WOOL);
        sampleTeam.addPlayer(new BedwarsPlayer("028fce20-ab39-4bd3-b829-8e027ee6a72b", sampleTeam));
        sampleTeam.addPlayer(new BedwarsPlayer("4751d842-0779-4632-adac-852ec5e3b6de", sampleTeam));
        teams.add(sampleTeam);

        itemShops.add(new Location(world, -19, 102, 24));

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
    }

    public void tick(long delta) {
        for(ItemGen gen : itemGens) {
            gen.tick(delta);
        }

        for(BedwarsTeam team : teams) {
            for(BedwarsPlayer player : team.getTeamPlayers().values()) {
                player.tick(delta);
            }
        }
    }

    public void handleBreakBed(Location location) {
        BedwarsTeam team = null;
        for(BedwarsTeam eachTeam : teams) {
            if(eachTeam.getBedLocation().equals(location)) {
                team = eachTeam;
                break;
            }
        }

        if(team == null) {
            return;
        }

        if(team.isBedBroken()) {
            return;
        }

        team.setBedBroken(true);

        for(BedwarsPlayer player : team.getTeamPlayers().values()) {
            Player bukkitPlayer = Bukkit.getServer().getPlayer(UUID.fromString(player.getUuid()));
            if(bukkitPlayer.isOnline()) {
                bukkitPlayer.playSound(bukkitPlayer.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 10, 1);
                bukkitPlayer.sendTitle(ChatColor.RED + "BED DESTROYED", ChatColor.YELLOW + "You will no longer respawn when you die!", 5, 80, 10);
            }
        }
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

    @Override
    public void dispose() {
        for(ItemGen gen : itemGens) {
            gen.dispose();
        }

        for(LivingEntity entity : shopVillagers) {
            entity.remove();
        }
    }

    public World getWorld() {
        return world;
    }

    public List<LivingEntity> getShopVillagers() {
        return new ArrayList<>(shopVillagers);
    }
}
