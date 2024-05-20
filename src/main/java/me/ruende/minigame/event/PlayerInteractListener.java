package me.ruende.minigame.event;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        boolean isBowInMainHand = player.getInventory().getItemInMainHand().getType() == Material.BOW;
        boolean isArrowInOffHand = player.getInventory().getItemInOffHand().getType() == Material.ARROW;
        boolean isRightClick = event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK;

        if (isBowInMainHand && !isArrowInOffHand && isRightClick) {
            event.setCancelled(true);
            player.sendMessage("왼손에 화살이 있어야 발사할 수 있습니다.");
        }
    }
}