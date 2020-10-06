package com.jaeheonshim.bedwars;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

import java.time.Duration;

public class EmeraldGen extends ItemGen {
    private ArmorStand as;

    public EmeraldGen(Location location) {
        super(new ItemStack(Material.EMERALD), Duration.ofSeconds(60).toMillis(), location);
        as = location.getWorld().spawn(location, ArmorStand.class);
        as.setCustomNameVisible(true);
        as.setVisible(false);
    }

    @Override
    public void tick(long delta) {
        super.tick(delta);
        long secs = Math.round((intervalMillis - spawnTimer) / 1000.0);
        as.setCustomName(ChatColor.BOLD + "" +ChatColor.GREEN + "Spawning emerald in: " + ChatColor.AQUA + secs + ChatColor.GREEN + " seconds");
    }
}
