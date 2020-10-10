package com.jaeheonshim.bedwars.shop.weapons;

import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import com.jaeheonshim.bedwars.shop.ShopItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class DiamondSwordItem extends ShopItem {
    @Override
    public Material getMaterial() {
        return Material.EMERALD;
    }

    @Override
    public int getCost() {
        return 4;
    }

    @Override
    public ItemStack getItem(BedwarsPlayer player) {
        ItemStack stack = new ItemStack(Material.DIAMOND_SWORD, 1);
        if(player.getTeam().isSharpness()) {
            stack.addEnchantment(Enchantment.DAMAGE_ALL, 3);
        }

        return stack;
    }

    @Override
    public String getName() {
        return "Diamond Sword";
    }
}
