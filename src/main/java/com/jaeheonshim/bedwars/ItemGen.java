package com.jaeheonshim.bedwars;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public abstract class ItemGen implements Disposable {
    private ItemStack itemSpawn;
    protected long intervalMillis;
    protected Location spawnLocation;

    protected long spawnTimer;

    public ItemGen(ItemStack items, long intervalMillis, Location location) {
        spawnLocation = location;
        this.itemSpawn = items;
        this.intervalMillis = intervalMillis;

        location.add(0.5, 0, 0.5);
    }

    public void tick(long delta) {
        if(spawnTimer >= intervalMillis) {
            Item entity = spawnLocation.getWorld().dropItem(spawnLocation, itemSpawn);
            entity.setVelocity(new Vector(0, 0, 0));
            spawnTimer = 0;
        } else {
            spawnTimer += delta;
        }
    }

    public void setInterval(long t) {
        this.intervalMillis = t;
    }
}
