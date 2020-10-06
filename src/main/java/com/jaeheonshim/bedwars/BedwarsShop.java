package com.jaeheonshim.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BedwarsShop {
    public static Inventory getInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9, "Bedwars Shop");
        inventory.setItem(0, new ItemStack(Material.WHITE_WOOL));
        inventory.setItem(1, new ItemStack(Material.WOODEN_SWORD));

        return inventory;
    }
}
