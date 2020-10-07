package com.jaeheonshim.bedwars.shop;

import com.jaeheonshim.bedwars.BedwarsPlayer;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class KnockbackStickItem implements ShopItem {
    @Override
    public Material getMaterial() {
        return Material.GOLD_INGOT;
    }

    @Override
    public int getCost() {
        return 5;
    }

    @Override
    public ItemStack getItem(BedwarsPlayer player) {
        ItemStack itemStack = new ItemStack(Material.STICK, 1);
        itemStack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);

        return itemStack;
    }

    @Override
    public String getName() {
        return "Knockback Stick";
    }
}
