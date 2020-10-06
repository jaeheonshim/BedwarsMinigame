package com.jaeheonshim.bedwars.shop;

import com.jaeheonshim.bedwars.BedwarsGameManager;
import com.jaeheonshim.bedwars.BedwarsPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Arrays;
import java.util.List;

// Note there should not be more than one of the same type of Material in the shop
public class BedwarsShop {
    private static final List<ShopItem> items = Arrays.asList(
            new WoolItem()
    );
    public static final String TITLE = "Bedwars Shop";

    public static Inventory getInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9, TITLE);
        for(ShopItem item : items) {
            inventory.addItem(item.getItem(BedwarsGameManager.getInstance().getBedwarsPlayer(player.getUniqueId().toString())));
        }

        return inventory;
    }

    public static String getTitle() {
        return TITLE;
    }

    public static boolean purchaseItem(Player player, ItemStack stack) {
        BedwarsPlayer bedwarsPlayer = BedwarsGameManager.getInstance().getBedwarsPlayer(player.getUniqueId().toString());

        for(ShopItem item : items) {
            Bukkit.getLogger().info(item.getMaterial().toString());
            if(item.getItem(bedwarsPlayer).getType() == stack.getType()) {
                PlayerInventory playerInventory = player.getInventory();
                if (playerInventory.containsAtLeast(new ItemStack(item.getMaterial()), item.getCost())) {
                    removeItem(player, new ItemStack(item.getMaterial()), item.getCost());
                    playerInventory.addItem(item.getItem(bedwarsPlayer));
                    player.updateInventory();
                    player.sendMessage(ChatColor.GREEN + "You purchased " + item.getName() + ".");
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "Not enough resources to purchase!");
                }
            }
        }

        return false;
    }

    public static void removeItem(Player player, ItemStack item, int amount) {
        for(int i = 0; i < amount; i++)
            player.getInventory().removeItem(item);
    }
}
