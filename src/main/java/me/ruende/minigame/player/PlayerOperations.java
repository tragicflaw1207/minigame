package me.ruende.minigame.player;

import org.bukkit.entity.Player;

public interface PlayerOperations {
    void giveItems(Player player);
    void removeItems(Player player);
    int getRemainingArrows(Player player);
}