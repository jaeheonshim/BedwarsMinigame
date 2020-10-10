package com.jaeheonshim.bedwars.shop;

import com.jaeheonshim.bedwars.BedwarsGameManager;
import com.jaeheonshim.bedwars.Util;
import com.jaeheonshim.bedwars.domain.BedwarsPlayer;
import com.jaeheonshim.bedwars.shop.team.SharpenedSwords;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class BedwarsTeamShop {
    private static final List<TeamShopItem> items = Arrays.asList(
            null, null, null, null, null, null, null, null, null,
            null, new SharpenedSwords()
    );
    public static final String TITLE = "Team Upgrades";

    public static Inventory getInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 36, TITLE);
        for(int i = 0; i < items.size(); i++) {
            TeamShopItem item = items.get(i);
            if(item != null) {
                ItemStack stack = item.getItem(BedwarsGameManager.getInstance().getBedwarsPlayer(player.getUniqueId().toString()), player);
                ItemMeta meta = stack.getItemMeta();
                if(meta != null) {
                    meta.setLore(Arrays.asList(ChatColor.YELLOW + "" + ChatColor.BOLD + "COST: " + ChatColor.RESET + ChatColor.AQUA + item.getCost() + " " + Util.friendlyCurrencyName(item.getMaterial())));
                }
                stack.setItemMeta(meta);

                inventory.setItem(i, stack);
            }
        }

        return inventory;
    }

    public static boolean purchaseItem(Player player, ItemStack stack) {
        BedwarsPlayer bwPlayer = BedwarsGameManager.getInstance().getBedwarsPlayer(player.getUniqueId().toString());
        for(TeamShopItem item : items) {
            if(item != null && item.getItem(bwPlayer, player).getType() == stack.getType()) {
                PlayerInventory inventory = player.getInventory();
                if(inventory.containsAtLeast(new ItemStack(item.getMaterial()), item.getCost())) {
                    String res = item.modifyPlayer(bwPlayer.getTeam(), player);

                    if(res != null) {
                        player.sendMessage(res);
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                        return false;
                    }

                    removeItem(player, new ItemStack(item.getMaterial()), item.getCost());

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
