package com.jaeheonshim.bedwars;

import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.*;

public class BedwarsGame implements Disposable {
    private List<ItemGen> itemGens = new ArrayList<>();
    private List<BedwarsTeam> teams = new ArrayList<>();

    public BedwarsGame() {
        itemGens.add(new EmeraldGen(new Location(Bukkit.getServer().getWorlds().get(0), 0, 100.5, 0)));
        itemGens.add(new EmeraldGen(new Location(Bukkit.getServer().getWorlds().get(0), -19, 100.6, 0)));
        itemGens.add(new IronGen(new Location(Bukkit.getServer().getWorlds().get(0), -14, 101, 26)));
        BedwarsTeam sampleTeam = new BedwarsTeam(new Location(Bukkit.getServer().getWorlds().get(0), -14, 101, 20));
        sampleTeam.addPlayer(new BedwarsPlayer("028fce20-ab39-4bd3-b829-8e027ee6a72b"));
        teams.add(sampleTeam);
    }

    public void tick(long delta) {
        for(ItemGen gen : itemGens) {
            gen.tick(delta);
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

    @Override
    public void dispose() {
        for(ItemGen gen : itemGens) {
            gen.dispose();
        }
    }
}
