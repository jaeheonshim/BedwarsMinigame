package com.jaeheonshim.bedwars.domain;

import com.jaeheonshim.bedwars.Disposable;
import com.jaeheonshim.bedwars.Util;
import com.jaeheonshim.bedwars.generator.EmeraldGen;
import com.jaeheonshim.bedwars.generator.GoldGen;
import com.jaeheonshim.bedwars.generator.IronGen;
import com.jaeheonshim.bedwars.generator.ItemGen;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.*;

public class BedwarsTeam implements Disposable {
    private List<ItemGen> generators = new ArrayList<>();
    private DyeColor teamColor;
    private boolean isBedBroken;
    private Location bedLocation;
    private Location respawnLocation;
    private Location genLocation;

    private BaseGenLevel genLevel = BaseGenLevel.NONE;
    private ArmorEnchantLevel armorEnchantLevel = ArmorEnchantLevel.NONE;

    private boolean sharpness;

    private Map<String, BedwarsPlayer> teamPlayers = new HashMap();

    public BedwarsTeam(Location bedLocation, Location respawnLocation, Location genLocation, DyeColor teamColor) {
        this.bedLocation = bedLocation;
        this.respawnLocation = respawnLocation;
        this.teamColor = teamColor;
        this.genLocation = genLocation;

        generators.add(new IronGen(genLocation));
        generators.add(new GoldGen(genLocation));
    }

    public void addPlayer(BedwarsPlayer player) {
        teamPlayers.put(player.getUuid(), player);
    }

    public void addGenerator(ItemGen gen) {
        this.generators.add(gen);
    }

    public List<ItemGen> getGenerators() {
        return generators;
    }

    public Map<String, BedwarsPlayer> getTeamPlayers() {
        return teamPlayers;
    }

    public Material getWool() {
        return Util.getWoolFromDye(teamColor);
    }

    public void setBedBroken(boolean bedBroken) {
        isBedBroken = bedBroken;
    }

    public Location getBedLocation() {
        return bedLocation;
    }

    public boolean isBedBroken() {
        return isBedBroken;
    }

    public Location getRespawnLocation() {
        return respawnLocation;
    }

    public DyeColor getTeamColor() {
        return teamColor;
    }

    public void setSharpness(boolean sharpness) {
        this.sharpness = sharpness;
        if(sharpness) {
            teamPlayers.values().forEach(BedwarsPlayer::updateSharpness);
        }
    }

    public boolean isSharpness() {
        return sharpness;
    }

    public void updateGenLevel(BaseGenLevel level) {
        Bukkit.getServer().broadcastMessage("Your gen has been upgraded to " + level.name());
        this.genLevel = level;
        for(ItemGen gen : generators) {
            if(gen instanceof IronGen) {
                gen.setInterval(level.getIron());
            } else if(gen instanceof GoldGen) {
                gen.setInterval(level.getGold());
            }
        }

        if(level == BaseGenLevel.EMERALD_FORGE) {
            generators.add(new EmeraldGen(genLocation, false));
        }
    }

    public BaseGenLevel getGenLevel() {
        return genLevel;
    }

    public void updateArmorEnchantLevel(ArmorEnchantLevel level) {
        this.armorEnchantLevel = level;
        for(BedwarsPlayer player : teamPlayers.values()) {
            player.setArmorEnchant(level);
        }
    }

    public ArmorEnchantLevel getArmorEnchantLevel() {
        return armorEnchantLevel;
    }

    @Override
    public void dispose() {
        for(ItemGen gen : generators) {
            gen.dispose();
        }
    }
}
