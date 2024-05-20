package me.ruende.minigame;

import me.ruende.minigame.arrow.ArrowEffectFactory;
import me.ruende.minigame.arrow.ArrowMetadataManager;
import me.ruende.minigame.commands.EndGameCommand;
import me.ruende.minigame.commands.StartGameCommand;
import me.ruende.minigame.event.*;
import me.ruende.minigame.game.GameManager;
import me.ruende.minigame.game.GameLifecycleManager;
import me.ruende.minigame.player.PlayerManager;
import me.ruende.minigame.target.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public final class MiniGame extends JavaPlugin {
    private GameLifecycleManager gameLifecycleManager;

    @Override
    public void onEnable() {
        Set<Location> targetBlocks = new HashSet<>();
        TargetCreator targetCreator = new TargetCreatorImpl(targetBlocks);
        TargetRemover targetRemover = new TargetRemoverImpl(targetBlocks);
        TargetChecker targetChecker = new TargetCheckerImpl(targetBlocks);
        PlayerManager playerManager = new PlayerManager();
        gameLifecycleManager = new GameLifecycleManager(targetCreator, targetRemover, targetChecker, playerManager);
        GameManager gameManager = new GameManager(new ArrowEffectFactory(), targetChecker, gameLifecycleManager);

        Bukkit.getPluginManager().registerEvents(new ArrowShootListener(new ArrowMetadataManager(this)), this);
        Bukkit.getPluginManager().registerEvents(new ArrowHitListener(gameManager), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDropItemListener(), this);

        registerCommands();
    }

    @Override
    public void onDisable() {
        gameLifecycleManager.endAllGames();
    }

    private void registerCommands() {
        CommandMap commandMap = Bukkit.getCommandMap();
        commandMap.register("minigame", new StartGameCommand("start", gameLifecycleManager));
        commandMap.register("minigame", new EndGameCommand("end", gameLifecycleManager));
    }
}