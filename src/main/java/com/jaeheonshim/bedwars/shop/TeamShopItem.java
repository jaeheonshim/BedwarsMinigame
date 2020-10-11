package com.jaeheonshim.bedwars.shop;

import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import com.jaeheonshim.bedwars.domain.BedwarsTeam;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class TeamShopItem {
    public abstract Material getMaterial();
    public abstract int getCost(BedwarsTeam team);
    public abstract ItemStack getItem(BedwarsPlayer bwplayer, Player player);
    public abstract String modifyPlayer(BedwarsTeam team, Player player);
    public abstract String getName(BedwarsTeam team);
}
