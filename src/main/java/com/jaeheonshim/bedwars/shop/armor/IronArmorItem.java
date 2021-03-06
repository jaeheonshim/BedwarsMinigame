package com.jaeheonshim.bedwars.shop.armor;

import com.jaeheonshim.bedwars.domain.ArmorLevel;
import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import com.jaeheonshim.bedwars.shop.ShopItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class IronArmorItem extends ShopItem {
    @Override
    public Material getMaterial() {
        return Material.GOLD_INGOT;
    }

    @Override
    public int getCost() {
        return 12;
    }

    @Override
    public ItemStack getItem(BedwarsPlayer player) {
        return new ItemStack(Material.IRON_BOOTS);
    }

    @Override
    public String getName() {
        return "Iron Armor";
    }

    @Override
    public String modifyPlayer(BedwarsPlayer bwplayer, Player player) {
        if(bwplayer.getArmorLevel().getLevel() >= ArmorLevel.IRON.getLevel()) {
            return ChatColor.RED + "You already have equal or better armor!";
        }

        bwplayer.setArmorLevel(ArmorLevel.IRON);
        return null;
    }
}