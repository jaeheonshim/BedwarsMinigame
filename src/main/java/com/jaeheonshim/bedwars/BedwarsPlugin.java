package com.jaeheonshim.bedwars;

import com.jaeheonshim.bedwars.listeners.BedBreakListener;
import com.jaeheonshim.bedwars.listeners.EntityDamageListener;
import com.jaeheonshim.bedwars.listeners.EntityInteractListener;
import com.jaeheonshim.bedwars.listeners.PlayerDamageListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BedwarsPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new BedBreakListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityInteractListener(), this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new GameTicker(), 0, 10);
    }

    @Override
    public void onDisable() {
        BedwarsGameManager.getInstance().dispose();
    }
}
