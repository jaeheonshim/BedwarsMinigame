package com.jaeheonshim.bedwars;

import org.bukkit.Color;
import org.bukkit.Location;

import java.util.*;

public class BedwarsTeam {
    private Color bedColor;
    private boolean isBedBroken;
    private Location bedLocation;

    private Map<String, BedwarsPlayer> teamPlayers = new HashMap();

    public BedwarsTeam(Location bedLocation) {
        this.bedLocation = bedLocation;
    }

    public void addPlayer(BedwarsPlayer player) {
        teamPlayers.put(player.getUuid(), player);
    }

    public Map<String, BedwarsPlayer> getTeamPlayers() {
        return teamPlayers;
    }

    public Color getBedColor() {
        return bedColor;
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
}
