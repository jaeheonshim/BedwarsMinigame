package com.jaeheonshim.bedwars.generator;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.time.Duration;

public class IronGen extends ItemGen {
    public IronGen(Location location) {
        super(new ItemStack(Material.IRON_INGOT), Duration.ofSeconds(1).toMillis(), location);
    }

    @Override
    public void dispose() {

    }
}
