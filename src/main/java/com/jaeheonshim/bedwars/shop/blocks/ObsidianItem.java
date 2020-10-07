package com.jaeheonshim.bedwars.shop.blocks;

import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import com.jaeheonshim.bedwars.shop.ShopItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ObsidianItem extends ShopItem {
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
    return new ItemStack(Material.OBSIDIAN, 4);
    }

    @Override
    public String getName() {
        return "Obsidian";
    }
}
