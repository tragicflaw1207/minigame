package me.ruende.minigame.event;

import me.ruende.minigame.arrow.ArrowMetadataManager;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

public class ArrowShootListener implements Listener {
    private final ArrowMetadataManager arrowMetadataManager;

    public ArrowShootListener(ArrowMetadataManager arrowMetadataManager) {
        this.arrowMetadataManager = arrowMetadataManager;
    }

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player player) {
            Arrow arrow = (Arrow) event.getProjectile();
            arrowMetadataManager.setArrowMetadata(player, arrow);
        }
    }
}