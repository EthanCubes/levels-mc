package com.ethancubes.levels;

import com.ethancubes.levels.commands.ExtractLevelCommand;
import com.ethancubes.levels.commands.RefreshLevelCommand;
import com.ethancubes.levels.listeners.PlayerDeathListener;
import com.ethancubes.levels.listeners.XPChangeListener;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class Levels extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("The plugin has loaded! yay!");
        // Plugin startup logic

        // Event listeners that help configure the amount of hearts the player gets
        getServer().getPluginManager().registerEvents(new XPChangeListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);

        // Imports the /extract command
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            event.registrar().register(ExtractLevelCommand.create());
        });

        // Imports the /refresh command
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            event.registrar().register(RefreshLevelCommand.create());
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
