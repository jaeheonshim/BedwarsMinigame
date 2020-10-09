package com.jaeheonshim.bedwars.shop.armor;

import com.jaeheonshim.bedwars.domain.ArmorLevel;
import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import com.jaeheonshim.bedwars.shop.ShopItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ChainmailArmorItem extends ShopItem {
    @Override
    public Material getMaterial() {
        return Material.IRON_INGOT;
    }

    @Override
    public int getCost() {
        return 40;
    }

    @Override
    public ItemStack getItem(BedwarsPlayer player) {
        return new ItemStack(Material.CHAINMAIL_BOOTS);
    }

    @Override
    public String getName() {
        return "Chainmail Armor";
    }

    @Override
    public String modifyPlayer(BedwarsPlayer bwplayer, Player player) {
        if(bwplayer.getArmorLevel().getLevel() >= ArmorLevel.CHAINMAIL.getLevel()) {
            return ChatColor.RED + "You already have equal or better armor!";
        }

        bwplayer.setArmorLevel(ArmorLevel.CHAINMAIL);
        return null;
    }
}
