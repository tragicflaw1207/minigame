package me.ruende.minigame.arrow.effect;

import org.bukkit.Location;
import org.bukkit.Material;

public class Type1ArrowEffect implements ArrowEffect {
    @Override
    public void applyEffect(Location hitLocation) {
        hitLocation.getBlock().setType(Material.AIR);
    }
}