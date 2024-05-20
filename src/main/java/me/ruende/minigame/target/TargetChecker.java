package me.ruende.minigame.target;

import org.bukkit.Location;
import org.bukkit.World;

public interface TargetChecker {
    boolean isTargetBlock(Location location);
    boolean allBlocksDestroyed(World world);
    int getRemainingBlocks(World world);
}