package com.jaeheonshim.bedwars;

import org.bukkit.Material;

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
            Material.ORANGE_WOOL,
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

    public static final String friendlyCurrencyName(Material material) {
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
}
