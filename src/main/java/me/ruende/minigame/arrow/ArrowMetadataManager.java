package me.ruende.minigame.arrow;

import me.ruende.minigame.MiniGame;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Objects;

public class ArrowMetadataManager {
    private final MiniGame plugin;

    public ArrowMetadataManager(MiniGame plugin) {
        this.plugin = plugin;
    }

    public void setArrowMetadata(Player player, Arrow arrow) {
        ItemMeta meta = player.getInventory().getItemInOffHand().getItemMeta();
        if (meta != null && meta.displayName() instanceof TextComponent) {
            String displayName = ((TextComponent) Objects.requireNonNull(meta.displayName())).content();
            arrow.setMetadata("화살 타입", new FixedMetadataValue(plugin, displayName));
        }
    }
}