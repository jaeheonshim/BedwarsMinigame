package com.jaeheonshim.bedwars.shop;

import com.jaeheonshim.bedwars.BedwarsPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface ShopItem {
    /**
     * Returns the type of ore is required to purchase this item
     * @return Bukkit Material enumeration
     */
    Material getMaterial();

    int getCost();

    /**
     * Returns the item the player recieves when they purchase the item
     * @param player Player purchasing item (for customization)
     * @return The itemstack the player is purchasing
     */
    ItemStack getItem(BedwarsPlayer player);

    String getName();
}
