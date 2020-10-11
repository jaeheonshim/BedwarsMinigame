package com.jaeheonshim.bedwars.shop.team;

import com.jaeheonshim.bedwars.domain.BaseGenLevel;
import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import com.jaeheonshim.bedwars.domain.BedwarsTeam;
import com.jaeheonshim.bedwars.shop.TeamShopItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ForgeUpgrade extends TeamShopItem {
    @Override
    public Material getMaterial() {
        return Material.DIAMOND;
    }

    @Override
    public int getCost(BedwarsTeam team) {
        switch(team.getGenLevel()) {
            case NONE:
                return 4;
            case IRON_FORGE:
                return 8;
            case GOLD_FORGE:
                return 12;
            case EMERALD_FORGE:
                return 16;
            default:
                return -1;
        }
    }

    @Override
    public ItemStack getItem(BedwarsPlayer bwplayer, Player player) {
        ItemStack stack = new ItemStack(Material.FURNACE);
        String name;
        switch(bwplayer.getTeam().getGenLevel()) {
            case NONE:
                name = "Iron Forge";
                break;
            case IRON_FORGE:
                name = "Gold Forge";
                break;
            case GOLD_FORGE:
                name = "Emerald Forge";
                break;
            case EMERALD_FORGE:
                name = "Molten Forge";
                break;
            case MOLTEN_FORGE:
                name = ChatColor.RED + "Maxed Forge!";
                break;
            default:
                name = null;
                break;
        }

        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(name);
        stack.setItemMeta(meta);

        return stack;
    }

    @Override
    public String modifyPlayer(BedwarsTeam team, Player player) {
        int ordinal = team.getGenLevel().ordinal();
        if(ordinal == BaseGenLevel.values().length - 1) {
            return ChatColor.RED + "You already have the maximum generator upgrade!";
        }
        team.updateGenLevel(BaseGenLevel.values()[ordinal + 1]);
        return null;
    }

    @Override
    public String getName(BedwarsTeam team) {
        switch(team.getGenLevel()) {
            case IRON_FORGE:
                return "Iron Forge";
            case GOLD_FORGE:
                return "Gold Forge";
            case EMERALD_FORGE:
                return "Emerald Forge";
            case MOLTEN_FORGE:
                return "Molten Forge";
        }

        return null;
    }
}
