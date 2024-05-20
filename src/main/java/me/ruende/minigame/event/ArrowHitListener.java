package me.ruende.minigame.event;

import me.ruende.minigame.game.GameManager;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ArrowHitListener implements Listener {
    private final GameManager gameManager;

    public ArrowHitListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow arrow && arrow.getShooter() instanceof Player player) {
            Block hitBlock = event.getHitBlock();
            if (hitBlock != null) {
                gameManager.handleArrowHit(player, arrow, hitBlock);
            }
        }
    }
}