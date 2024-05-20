package me.ruende.minigame.target;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.Set;

public class TargetCreatorImpl implements TargetCreator {
    private final Set<Location> targetBlocks;

    public TargetCreatorImpl(Set<Location> targetBlocks) {
        this.targetBlocks = targetBlocks;
    }

    @Override
    public void createTarget(Location centerLocation, World world) {
        int centerX = centerLocation.getBlockX();
        int centerY = centerLocation.getBlockY();
        int centerZ = centerLocation.getBlockZ();

        int startY = centerY - 3;
        int startZ = centerZ - 3;

        for (int y = startY; y < startY + 7; y++) {
            for (int z = startZ; z < startZ + 7; z++) {
                Location blockLocation = new Location(world, centerX, y, z);
                setBlock(blockLocation);
                targetBlocks.add(blockLocation);
            }
        }
    }

    private void setBlock(Location location) {
        location.getWorld().getBlockAt(location).setType(Material.TARGET);
    }
}