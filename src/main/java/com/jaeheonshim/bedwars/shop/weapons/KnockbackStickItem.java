package com.jaeheonshim.bedwars.shop.weapons;

import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import com.jaeheonshim.bedwars.shop.ShopItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class KnockbackStickItem extends ShopItem {
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
