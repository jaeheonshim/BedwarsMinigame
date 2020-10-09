package com.jaeheonshim.bedwars.shop;

import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class ShopItem {
    /**
     * Returns the type of ore is required to purchase this item
     * @return Bukkit Material enumeration
     */
    public abstract Material getMaterial();

    public abstract int getCost();

    /**
     * Returns the item the player recieves when they purchase the item
     * @param player Player purchasing item (for customization)
     * @return The itemstack the player is purchasing
     */
    public abstract ItemStack getItem(BedwarsPlayer player);

    public abstract String getName();

    public String modifyPlayer(BedwarsPlayer bwplayer, Player player) {
        player.getInventory().addItem(getItem(bwplayer));

        return null;
    }
}
