package com.jaeheonshim.bedwars.shop.weapons;

import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import com.jaeheonshim.bedwars.shop.ShopItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class StoneSwordItem extends ShopItem {
    @Override
    public Material getMaterial() {
        return Material.IRON_INGOT;
    }

    @Override
    public int getCost() {
        return 10;
    }

    @Override
    public ItemStack getItem(BedwarsPlayer player) {
        ItemStack stack = new ItemStack(Material.STONE_SWORD, 1);
        if(player.getTeam().isSharpness()) {
            stack.addEnchantment(Enchantment.DAMAGE_ALL, 3);
        }

        return stack;
    }

    @Override
    public String getName() {
        return "Stone Sword";
    }
}
