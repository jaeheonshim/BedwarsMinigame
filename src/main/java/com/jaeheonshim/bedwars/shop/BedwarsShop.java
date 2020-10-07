package com.jaeheonshim.bedwars.shop;

import com.jaeheonshim.bedwars.BedwarsGameManager;
import com.jaeheonshim.bedwars.BedwarsPlayer;
import com.jaeheonshim.bedwars.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

// Note there should not be more than one of the same type of Material in the shop
public class BedwarsShop {
    private static final List<ShopItem> items = Arrays.asList(
            null, null, null, null, null, null, null, null, null,
            null, new WoolItem(), new WoodItem(), new EndStoneItem(), new ObsidianItem(), null, null, null, null,
            null, new StoneSwordItem(), new IronSwordItem(), new DiamondSwordItem(), new KnockbackStickItem()
    );
    public static final String TITLE = "Item Shop";

    public static Inventory getInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 36, TITLE);
        for(int i = 0; i < items.size(); i++) {
            ShopItem item = items.get(i);
            if(item != null) {
                ItemStack itemStack = item.getItem(BedwarsGameManager.getInstance().getBedwarsPlayer(player.getUniqueId().toString()));
                ItemMeta meta = itemStack.getItemMeta();
                if(meta != null)
                    meta.setLore(Arrays.asList(ChatColor.YELLOW + "" + ChatColor.BOLD + "COST: " + ChatColor.RESET + ChatColor.AQUA + item.getCost() + " " + Util.friendlyCurrencyName(item.getMaterial())));
                itemStack.setItemMeta(meta);

                inventory.setItem(i, itemStack);
            }
        }

        return inventory;
    }

    public static String getTitle() {
        return TITLE;
    }

    public static boolean purchaseItem(Player player, ItemStack stack) {
        BedwarsPlayer bedwarsPlayer = BedwarsGameManager.getInstance().getBedwarsPlayer(player.getUniqueId().toString());

        for(ShopItem item : items) {
            if(item != null && item.getItem(bedwarsPlayer).getType() == stack.getType()) {
                PlayerInventory playerInventory = player.getInventory();
                if (playerInventory.containsAtLeast(new ItemStack(item.getMaterial()), item.getCost())) {
                    removeItem(player, new ItemStack(item.getMaterial()), item.getCost());
                    playerInventory.addItem(item.getItem(bedwarsPlayer));
                    player.updateInventory();
                    player.sendMessage(ChatColor.GREEN + "You purchased " + item.getName() + ".");
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 10, 7);
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "Not enough resources to purchase!");
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
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
