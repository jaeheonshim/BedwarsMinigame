package com.jaeheonshim.bedwars;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.time.Duration;

public class DiamondGen extends ItemGen {
    public DiamondGen(Location location) {
        super(new ItemStack(Material.DIAMOND), Duration.ofMinutes(1).plus(Duration.ofSeconds(30)).toMillis(), location);
    }
}