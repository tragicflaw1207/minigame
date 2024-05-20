package me.ruende.minigame.game;

import me.ruende.minigame.player.PlayerOperations;
import me.ruende.minigame.target.TargetCreator;
import me.ruende.minigame.target.TargetRemover;
import me.ruende.minigame.target.TargetChecker;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameLifecycleManager {
    private final TargetCreator targetCreator;
    private final TargetRemover targetRemover;
    private final TargetChecker targetChecker;
    private final PlayerOperations playerOperations;
    private final Map<Player, Long> playerStartTime = new ConcurrentHashMap<>();

    public GameLifecycleManager(TargetCreator targetCreator, TargetRemover targetRemover, TargetChecker targetChecker, PlayerOperations playerOperations) {
        this.targetCreator = targetCreator;
        this.targetRemover = targetRemover;
        this.targetChecker = targetChecker;
        this.playerOperations = playerOperations;
    }

    public void startGame(Player player, World world) {
        if (playerStartTime.putIfAbsent(player, System.currentTimeMillis()) != null) {
            player.sendMessage("게임이 이미 진행 중입니다!");
            return;
        }

        Location targetLocation = new Location(world, 0, 150, 0);
        targetCreator.createTarget(targetLocation, world);
        playerOperations.giveItems(player);
        player.sendMessage("게임이 시작되었습니다!");
    }

    public void endGame(Player player, World world) {
        if (!playerStartTime.containsKey(player)) {
            player.sendMessage("진행 중인 게임이 없습니다!");
            return;
        }

        long playTime = System.currentTimeMillis() - playerStartTime.get(player);
        int remainingArrows = playerOperations.getRemainingArrows(player);
        int remainingTargetBlocks = targetChecker.getRemainingBlocks(world);

        displayGameSummary(player, playTime, remainingArrows, remainingTargetBlocks);

        targetRemover.removeTarget(world);
        playerOperations.removeItems(player);
        playerStartTime.remove(player);
    }

    private void displayGameSummary(Player player, long playTime, int remainingArrows, int remainingTargetBlocks) {
        player.sendMessage("게임 종료!");
        player.sendMessage("플레이 타임: " + playTime / 1000 + "초");
        player.sendMessage("남은 화살: " + remainingArrows);
        player.sendMessage("남은 과녁 블록: " + remainingTargetBlocks);

        if (remainingTargetBlocks == 0) {
            player.sendMessage("게임에서 승리했습니다.");
        } else {
            player.sendMessage("게임에서 패배했습니다.");
        }
    }

    public void endAllGames() {
        for (Player player : playerStartTime.keySet()) {
            endGame(player, player.getWorld());
        }
    }
}