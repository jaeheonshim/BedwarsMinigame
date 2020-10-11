package com.jaeheonshim.bedwars;

import com.jaeheonshim.bedwars.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BedwarsPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new BedBreakListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new HungerTickListener(), this);
        Bukkit.getPluginManager().registerEvents(new PvpListener(), this);
        Bukkit.getPluginManager().registerEvents(new CraftingListener(), this);
        Bukkit.getPluginManager().registerEvents(new TntPlaceListener(), this);
        Bukkit.getPluginManager().registerEvents(new FireballLaunchListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerPickupItem(), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(), this);
        Bukkit.getPluginManager().registerEvents(new TntExplodeBlockListener(), this);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new GameTicker(), 0, 10);
    }

    @Override
    public void onDisable() {
        BedwarsGameManager.getInstance().dispose();
    }
}
