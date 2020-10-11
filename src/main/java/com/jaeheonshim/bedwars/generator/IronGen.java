package com.jaeheonshim.bedwars.generator;

import com.jaeheonshim.bedwars.domain.BaseGenLevel;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.time.Duration;

public class IronGen extends ItemGen {
    public IronGen(Location location) {
        super(new ItemStack(Material.IRON_INGOT), BaseGenLevel.DEFAULT_IRON, location);
    }

    @Override
    public void dispose() {

    }
}
