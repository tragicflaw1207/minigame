package me.ruende.minigame.target;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.Set;

public class TargetRemoverImpl implements TargetRemover {
    private final Set<Location> targetBlocks;

    public TargetRemoverImpl(Set<Location> targetBlocks) {
        this.targetBlocks = targetBlocks;
    }

    @Override
    public void removeTarget(World world) {
        targetBlocks.forEach(this::setBlock);
        targetBlocks.clear();
    }

    private void setBlock(Location location) {
        location.getWorld().getBlockAt(location).setType(Material.AIR);
    }
}