package me.ruende.minigame.item;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ItemRemover {
    public void removeArrows(Player player) {
        removeItem(player, Material.ARROW);
    }

    public void removeBow(Player player) {
        removeItem(player, Material.BOW);
    }

    public void clearOffHandItems(Player player) {
        Material offHandItemType = player.getInventory().getItemInOffHand().getType();
        if (offHandItemType == Material.ARROW || offHandItemType == Material.BOW) {
            player.getInventory().setItemInOffHand(null);
        }
    }

    private void removeItem(Player player, Material material) {
        player.getInventory().remove(material);
    }
}