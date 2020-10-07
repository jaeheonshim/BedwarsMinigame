package com.jaeheonshim.bedwars.shop;

import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DiamondSwordItem implements ShopItem {
    @Override
    public Material getMaterial() {
        return Material.EMERALD;
    }

    @Override
    public int getCost() {
        return 4;
    }

    @Override
    public ItemStack getItem(BedwarsPlayer player) {
        return new ItemStack(Material.DIAMOND_SWORD, 1);
    }

    @Override
    public String getName() {
        return "Diamond Sword";
    }
}
