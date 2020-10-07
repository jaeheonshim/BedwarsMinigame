package com.jaeheonshim.bedwars.shop;

import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WoodItem implements ShopItem {
    @Override
    public Material getMaterial() {
        return Material.GOLD_INGOT;
    }

    @Override
    public int getCost() {
        return 4;
    }

    @Override
    public ItemStack getItem(BedwarsPlayer player) {
        return new ItemStack(Material.OAK_PLANKS, 16);
    }

    @Override
    public String getName() {
        return "Wood";
    }
}
