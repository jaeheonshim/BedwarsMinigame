package com.jaeheonshim.bedwars.shop.team;

import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import com.jaeheonshim.bedwars.domain.BedwarsTeam;
import com.jaeheonshim.bedwars.shop.TeamShopItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SharpenedSwords extends TeamShopItem {
    @Override
    public Material getMaterial() {
        return Material.DIAMOND;
    }

    @Override
    public int getCost() {
        return 8;
    }

    @Override
    public ItemStack getItem(BedwarsPlayer bwplayer, Player player) {
        ItemStack stack = new ItemStack(Material.IRON_SWORD);
        stack.addEnchantment(Enchantment.DAMAGE_ALL, 3);

        return stack;
    }

    @Override
    public String modifyPlayer(BedwarsTeam bwteam, Player player) {
        if(!bwteam.isSharpness()) {
            bwteam.setSharpness(true);
            return null;
        } else {
            return ChatColor.RED + "Your team already has Sharpened Swords!";
        }
    }

    @Override
    public String getName() {
        return "Sharpened Swords";
    }
}
