package com.jaeheonshim.bedwars.shop;

import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WoolItem implements ShopItem {
    @Override
    public Material getMaterial() {
        return Material.IRON_INGOT;
    }

    @Override
    public int getCost() {
        return 4;
    }

    @Override
    public ItemStack getItem(BedwarsPlayer player) {
        return new ItemStack(player.getTeam().getWool(), 16);
    }

    @Override
    public String getName() {
        return "Wool";
    }
}
