package com.jaeheonshim.bedwars.generator;

import com.jaeheonshim.bedwars.domain.BaseGenLevel;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.time.Duration;

public class GoldGen extends ItemGen {
    public GoldGen(Location location) {
        super(new ItemStack(Material.GOLD_INGOT), BaseGenLevel.DEFAULT_GOLD, location);
    }

    @Override
    public void dispose() {

    }
}
