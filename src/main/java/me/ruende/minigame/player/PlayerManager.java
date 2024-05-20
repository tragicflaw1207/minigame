package me.ruende.minigame.player;

import me.ruende.minigame.item.ItemGiver;
import me.ruende.minigame.item.ItemRemover;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Objects;

public class PlayerManager implements PlayerOperations {
    private final ItemGiver itemGiver;
    private final ItemRemover itemRemover;

    public PlayerManager() {
        this.itemGiver = new ItemGiver();
        this.itemRemover = new ItemRemover();
    }

    @Override
    public void giveItems(Player player) {
        removeItems(player); // 기존 아이템을 제거
        itemGiver.giveArrows(player, 30, "타입 1 화살");
        itemGiver.giveArrows(player, 10, "타입 2 화살");
        itemGiver.giveArrows(player, 10, "타입 3 화살");
        itemGiver.giveBow(player);

        player.sendMessage("아이템이 지급되었습니다. 타입 1 화살: 30개, 타입 2 화살: 10개, 타입 3 화살: 10개");
    }

    @Override
    public void removeItems(Player player) {
        itemRemover.removeArrows(player);
        itemRemover.removeBow(player);
        itemRemover.clearOffHandItems(player);
    }

    public int getRemainingArrows(Player player) {
        return Arrays.stream(player.getInventory().getContents())
                .filter(Objects::nonNull)
                .filter(itemStack -> itemStack.getType() == Material.ARROW)
                .mapToInt(ItemStack::getAmount)
                .sum();
    }
}