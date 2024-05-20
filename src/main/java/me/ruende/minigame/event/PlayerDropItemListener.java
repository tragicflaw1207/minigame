package me.ruende.minigame.event;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener implements Listener {
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Material itemType = event.getItemDrop().getItemStack().getType();
        if (itemType == Material.BOW || itemType == Material.ARROW) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("이 아이템은 버릴 수 없습니다.");
        }
    }
}