package com.jaeheonshim.bedwars.shop;

import com.jaeheonshim.bedwars.BedwarsPlayer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StoneSwordItem implements ShopItem {
    @Override
    public Material getMaterial() {
        return Material.IRON_INGOT;
    }

    @Override
    public int getCost() {
        return 10;
    }

    @Override
    public ItemStack getItem(BedwarsPlayer player) {
        return new ItemStack(Material.STONE_SWORD, 1);
    }

    @Override
    public String getName() {
        return "Stone Sword";
    }
}
