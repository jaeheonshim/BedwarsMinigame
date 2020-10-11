package com.jaeheonshim.bedwars.generator;

import com.jaeheonshim.bedwars.Disposable;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

import java.time.Duration;

public class DiamondGen extends ItemGen implements Disposable {
    private ArmorStand as;

    public DiamondGen(Location location) {
        super(new ItemStack(Material.DIAMOND), Duration.ofSeconds(45).toMillis(), location);
        as = location.getWorld().spawn(location, ArmorStand.class);
        as.setCustomNameVisible(true);
        as.setVisible(false);
    }

    @Override
    public void tick(long delta) {
        super.tick(delta);
        long secs = Math.round((intervalMillis - spawnTimer) / 1000.0 + 1);
        as.setCustomName(ChatColor.BOLD + "" + ChatColor.AQUA + "Spawning diamond in: " + ChatColor.AQUA + secs + ChatColor.AQUA + " seconds");
    }

    @Override
    public void dispose() {
        as.remove();
    }
}