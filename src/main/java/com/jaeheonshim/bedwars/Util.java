package com.jaeheonshim.bedwars;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.material.Wool;

import java.util.*;

public class Util {
    public static final List<Material> beds = Arrays.asList(
            Material.WHITE_BED,
            Material.ORANGE_BED,
            Material.PINK_BED,
            Material.LIGHT_BLUE_BED,
            Material.YELLOW_BED,
            Material.LIME_BED,
            Material.MAGENTA_BED,
            Material.LIGHT_GRAY_BED,
            Material.GRAY_BED,
            Material.CYAN_BED,
            Material.PURPLE_BED,
            Material.BLUE_BED,
            Material.BROWN_BED,
            Material.GREEN_BED,
            Material.RED_BED,
            Material.BLACK_BED);

    public static final List<Material> wool = Arrays.asList(
            Material.WHITE_WOOL,
            Material.PINK_WOOL,
            Material.LIGHT_BLUE_WOOL,
            Material.YELLOW_WOOL,
            Material.LIME_WOOL,
            Material.MAGENTA_WOOL,
            Material.LIGHT_GRAY_WOOL,
            Material.GRAY_WOOL,
            Material.CYAN_WOOL,
            Material.PURPLE_WOOL,
            Material.BLUE_WOOL,
            Material.BROWN_WOOL,
            Material.GREEN_WOOL,
            Material.RED_WOOL,
            Material.BLACK_WOOL
    );

    public static final List<DyeColor> dyeColors = Arrays.asList(
            DyeColor.WHITE,
            DyeColor.PINK,
            DyeColor.LIGHT_BLUE,
            DyeColor.YELLOW,
            DyeColor.LIME,
            DyeColor.MAGENTA,
            DyeColor.LIGHT_GRAY,
            DyeColor.GRAY,
            DyeColor.CYAN,
            DyeColor.PURPLE,
            DyeColor.BLUE,
            DyeColor.BROWN,
            DyeColor.GREEN,
            DyeColor.RED,
            DyeColor.BLACK
    );

    public static final List<ChatColor> chatColors = Arrays.asList(
            ChatColor.WHITE,
            ChatColor.LIGHT_PURPLE,
            ChatColor.DARK_AQUA,
            ChatColor.YELLOW,
            ChatColor.GREEN,
            ChatColor.DARK_PURPLE,
            ChatColor.GRAY,
            ChatColor.DARK_GRAY,
            ChatColor.AQUA,
            ChatColor.DARK_PURPLE,
            ChatColor.BLUE,
            ChatColor.GOLD,
            ChatColor.DARK_GREEN,
            ChatColor.RED,
            ChatColor.BLACK
    );

    public static final List<Material> swords = Arrays.asList(Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.DIAMOND_SWORD);

    public static String friendlyCurrencyName(Material material) {
        switch(material) {
            case IRON_INGOT:
                return "Iron";
            case GOLD_INGOT:
                return "Gold";
            case EMERALD:
                return "Emerald";
            case DIAMOND:
                return "Diamond";
            default:
                return null;
        }
    }

    public static Material getWoolFromDye(DyeColor color) {
        return wool.get(dyeColors.indexOf(color));
    }


    public static ChatColor getChatFromDye(DyeColor color) {
        return chatColors.get(dyeColors.indexOf(color));
    }
}
