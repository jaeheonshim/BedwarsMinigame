package com.jaeheonshim.bedwars.generator;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.time.Duration;

public class GoldGen extends ItemGen {
    public GoldGen(Location location) {
        super(new ItemStack(Material.GOLD_INGOT), Duration.ofSeconds(12).toMillis(), location);
    }

    @Override
    public void dispose() {

    }
}
