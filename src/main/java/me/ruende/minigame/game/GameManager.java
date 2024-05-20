package me.ruende.minigame.game;

import me.ruende.minigame.arrow.effect.ArrowEffect;
import me.ruende.minigame.arrow.ArrowEffectFactory;
import me.ruende.minigame.target.TargetChecker;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Objects;

public class GameManager {
    private final ArrowEffectFactory arrowEffectFactory;
    private final TargetChecker targetChecker;
    private final GameLifecycleManager gameLifecycleManager;

    public GameManager(ArrowEffectFactory arrowEffectFactory, TargetChecker targetChecker,
                       GameLifecycleManager gameLifecycleManager) {
        this.arrowEffectFactory = arrowEffectFactory;
        this.targetChecker = targetChecker;
        this.gameLifecycleManager = gameLifecycleManager;
    }

    public void handleArrowHit(Player player, Arrow arrow, Block hitBlock) {
        Location hitLocation = hitBlock.getLocation();
        if (!isHitValid(player, arrow, hitLocation)) {
            arrow.remove();
            checkEndCondition(player);
            return;
        }

        applyArrowEffect(arrow, hitLocation);
        checkEndCondition(player);
    }

    private boolean isHitValid(Player player, Arrow arrow, Location hitLocation) {
        if (!targetChecker.isTargetBlock(hitLocation)) {
            return false;
        }

        if (!arrow.hasMetadata("화살 타입")) {
            return false;
        }

        double distance = calculateDistance(player, hitLocation);
        if (distance < 20) {
            player.sendMessage("거리가 너무 가까워 명중으로 인정되지 않습니다.");
            return false;
        }

        return true;
    }

    private void applyArrowEffect(Arrow arrow, Location hitLocation) {
        String arrowType = arrow.getMetadata("화살 타입").get(0).asString();
        ArrowEffect effect = arrowEffectFactory.getArrowEffect(arrowType);
        effect.applyEffect(hitLocation);
        arrow.remove();
    }

    public int getRemainingArrows(Player player) {
        return Arrays.stream(player.getInventory().getContents())
                .filter(Objects::nonNull)
                .filter(itemStack -> itemStack.getType() == Material.ARROW)
                .mapToInt(ItemStack::getAmount)
                .sum();
    }

    public boolean checkWinCondition(World world) {
        return targetChecker.allBlocksDestroyed(world);
    }

    public double calculateDistance(Player player, Location hitLocation) {
        return player.getLocation().distance(hitLocation);
    }

    public void checkEndCondition(Player player) {
        World world = player.getWorld();
        if (checkWinCondition(world) || getRemainingArrows(player) == 0) {
            gameLifecycleManager.endGame(player, world);
        }
    }
}