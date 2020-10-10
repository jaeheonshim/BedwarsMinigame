package com.jaeheonshim.bedwars.domain;

import com.jaeheonshim.bedwars.Util;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.*;

public class BedwarsTeam {
    private DyeColor teamColor;
    private boolean isBedBroken;
    private Location bedLocation;
    private Location respawnLocation;

    private boolean sharpness;

    private Map<String, BedwarsPlayer> teamPlayers = new HashMap();

    public BedwarsTeam(Location bedLocation, Location respawnLocation, DyeColor teamColor) {
        this.bedLocation = bedLocation;
        this.respawnLocation = respawnLocation;
        this.teamColor = teamColor;
    }

    public void addPlayer(BedwarsPlayer player) {
        teamPlayers.put(player.getUuid(), player);
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
}
