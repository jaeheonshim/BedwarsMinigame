package com.jaeheonshim.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BedwarsPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new BedBreakListener(), this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new GameTicker(), 0, 10);
    }

    @Override
    public void onDisable() {
        BedwarsGameManager.getInstance().dispose();
    }
}
