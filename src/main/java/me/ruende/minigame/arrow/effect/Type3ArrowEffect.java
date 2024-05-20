package me.ruende.minigame.arrow.effect;

import org.bukkit.Location;
import org.bukkit.Material;

public class Type3ArrowEffect implements ArrowEffect {
    @Override
    public void applyEffect(Location hitLocation) {
        for (int y = -1; y <= 1; y++) {
            hitLocation.clone().add(0, y, 0).getBlock().setType(Material.AIR);
        }
    }
}