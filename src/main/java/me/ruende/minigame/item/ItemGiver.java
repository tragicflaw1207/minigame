package me.ruende.minigame.item;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemGiver {
    public void giveArrows(Player player, int amount, String displayName) {
        ItemStack arrows = new ItemStack(Material.ARROW, amount);
        ItemMeta meta = arrows.getItemMeta();
        meta.displayName(Component.text(displayName));
        arrows.setItemMeta(meta);
        player.getInventory().addItem(arrows);
    }

    public void giveBow(Player player) {
        player.getInventory().addItem(new ItemStack(Material.BOW, 1));
    }
}