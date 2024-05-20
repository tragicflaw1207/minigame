package me.ruende.minigame.target;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.Set;


public class TargetCheckerImpl implements TargetChecker {
    private final Set<Location> targetBlocks;

    public TargetCheckerImpl(Set<Location> targetBlocks) {
        this.targetBlocks = targetBlocks;
    }

    @Override
    public boolean isTargetBlock(Location location) {
        return targetBlocks.contains(location);
    }

    @Override
    public boolean allBlocksDestroyed(World world) {
        return targetBlocks.stream()
                .allMatch(location -> world.getBlockAt(location).getType() == Material.AIR);
    }

    @Override
    public int getRemainingBlocks(World world) {
        return (int) targetBlocks.stream()
                .filter(location -> world.getBlockAt(location).getType() != Material.AIR)
                .count();
    }
}