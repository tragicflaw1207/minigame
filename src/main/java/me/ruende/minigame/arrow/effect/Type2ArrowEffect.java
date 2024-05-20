package me.ruende.minigame.arrow.effect;

import org.bukkit.Location;
import org.bukkit.Material;

public class Type2ArrowEffect implements ArrowEffect {
    @Override
    public void applyEffect(Location hitLocation) {
        for (int z = -1; z <= 1; z++) {
            hitLocation.clone().add(0, 0, z).getBlock().setType(Material.AIR);
        }
    }
}