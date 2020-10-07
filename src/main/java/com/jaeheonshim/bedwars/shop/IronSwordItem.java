package com.jaeheonshim.bedwars.shop;

import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class IronSwordItem implements ShopItem {
    @Override
    public Material getMaterial() {
        return Material.GOLD_INGOT;
    }

    @Override
    public int getCost() {
        return 7;
    }

    @Override
    public ItemStack getItem(BedwarsPlayer player) {
        return new ItemStack(Material.IRON_SWORD, 1);
    }

    @Override
    public String getName() {
        return "Iron Sword";
    }
}
