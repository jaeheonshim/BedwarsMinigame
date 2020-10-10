package com.jaeheonshim.bedwars.shop.weapons;

import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import com.jaeheonshim.bedwars.shop.ShopItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class IronSwordItem extends ShopItem {
    @Override
    public Material getMaterial() {
        return Material.GOLD_INGOT;
    }

    @Override
    public int getCost() {
        return 7;
    }

    @Override
    public ItemStack getItem(BedwarsPlayer player) {
        ItemStack stack = new ItemStack(Material.IRON_SWORD, 1);
        if(player.getTeam().isSharpness()) {
            stack.addEnchantment(Enchantment.DAMAGE_ALL, 3);
        }

        return stack;
    }

    @Override
    public String getName() {
        return "Iron Sword";
    }
}
