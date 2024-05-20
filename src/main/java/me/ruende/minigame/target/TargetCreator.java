package me.ruende.minigame.target;

import org.bukkit.Location;
import org.bukkit.World;

public interface TargetCreator {
    void createTarget(Location location, World world);
}