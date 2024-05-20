package me.ruende.minigame.commands;

import me.ruende.minigame.game.GameLifecycleManager;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StartGameCommand extends BukkitCommand {
    private final GameLifecycleManager gameLifecycleManager;

    public StartGameCommand(String name, GameLifecycleManager gameLifecycleManager) {
        super(name);
        this.gameLifecycleManager = gameLifecycleManager;
        this.description = "미니게임을 시작합니다.";
        this.usageMessage = "/start";
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage("이 커맨드는 플레이어만 실행할 수 있습니다.");
            return true;
        }

        gameLifecycleManager.startGame(player, player.getWorld());
        return true;
    }
}