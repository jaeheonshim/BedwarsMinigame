package com.jaeheonshim.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class BedwarsGame {
    private List<ItemGen> itemGens = new ArrayList<>();

    public BedwarsGame() {
        itemGens.add(new EmeraldGen(new Location(Bukkit.getServer().getWorlds().get(0), 0, 100.5, 0)));
        itemGens.add(new EmeraldGen(new Location(Bukkit.getServer().getWorlds().get(0), -19, 100.6, 0)));
    }

    public void tick(long delta) {
        for(ItemGen gen : itemGens) {
            gen.tick(delta);
        }
    }
}
