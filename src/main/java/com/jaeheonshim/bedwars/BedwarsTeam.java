package com.jaeheonshim.bedwars;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.*;

public class BedwarsTeam {
    private Material wool;
    private boolean isBedBroken;
    private Location bedLocation;
    private Location respawnLocation;

    private Map<String, BedwarsPlayer> teamPlayers = new HashMap();

    public BedwarsTeam(Location bedLocation, Location respawnLocation, Material wool) {
        this.bedLocation = bedLocation;
        this.respawnLocation = respawnLocation;
        this.wool = wool;
    }

    public void addPlayer(BedwarsPlayer player) {
        teamPlayers.put(player.getUuid(), player);
    }

    public Map<String, BedwarsPlayer> getTeamPlayers() {
        return teamPlayers;
    }

    public Material getWool() {
        return wool;
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

}
