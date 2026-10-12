package com.ethancubes.levels;

import com.ethancubes.levels.commands.ExtractLevelCommand;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class Levels extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Hello!");

        // Event listeners that help configure the amount of hearts the player gets

        // Imports the /extract command
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            event.registrar().register(ExtractLevelCommand.create());
        });

        // Imports the /transfer command
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
