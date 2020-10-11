package com.jaeheonshim.bedwars.shop.team;

import com.jaeheonshim.bedwars.domain.ArmorEnchantLevel;
import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import com.jaeheonshim.bedwars.domain.BedwarsTeam;
import com.jaeheonshim.bedwars.shop.TeamShopItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ArmorUpgrade extends TeamShopItem {
    @Override
    public Material getMaterial() {
        return Material.DIAMOND;
    }

    @Override
    public int getCost(BedwarsTeam team) {
        switch(team.getArmorEnchantLevel()) {
            case NONE:
                return 5;
            case PROT_I:
                return 10;
            case PROT_II:
                return 15;
            default:
                return -1;
        }
    }

    @Override
    public ItemStack getItem(BedwarsPlayer bwplayer, Player player) {
        ItemStack stack = new ItemStack(Material.IRON_CHESTPLATE);
        String name;
        switch(bwplayer.getTeam().getArmorEnchantLevel()) {
            case NONE:
                name = "Protection I";
                break;
            case PROT_I:
                name = "Protection II";
                break;
            case PROT_II:
                name = "Protection III";
                break;
            case PROT_III:
                name = ChatColor.RED + "Armor enchant level maxed!";
                break;
            default:
                name = null;
        }

        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(name);
        stack.setItemMeta(meta);

        return stack;
    }

    @Override
    public String modifyPlayer(BedwarsTeam team, Player player) {
        int currentOrdinal = team.getArmorEnchantLevel().ordinal();
        if(currentOrdinal >= ArmorEnchantLevel.values().length - 1) {
            return ChatColor.RED + "Your team already has the maximum armor enchantment level!";
        }

        team.updateArmorEnchantLevel(ArmorEnchantLevel.values()[currentOrdinal + 1]);
        return null;
    }

    @Override
    public String getName(BedwarsTeam team) {
        switch(team.getArmorEnchantLevel()) {
            case PROT_I:
                return "Protection I";
            case PROT_II:
                return "Protection II";
            case PROT_III:
                return "Protection III";
        }

        return null;
    }
}
