package com.jaeheonshim.bedwars.shop.blocks;

import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import com.jaeheonshim.bedwars.shop.ShopItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class EndStoneItem extends ShopItem {
    @Override
    public Material getMaterial() {
        return Material.IRON_INGOT;
    }

    @Override
    public int getCost() {
        return 24;
    }

    @Override
    public ItemStack getItem(BedwarsPlayer player) {
        return new ItemStack(Material.END_STONE, 12);
    }

    @Override
    public String getName() {
        return null;
    }
}
