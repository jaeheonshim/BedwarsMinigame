package com.jaeheonshim.bedwars;

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
}
