package com.ethancubes.levels.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void on_death(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        player.resetMaxHealth(); // Also deprecated, how do I change this? I don't want to ask on a forum because I'll get laughed at
        player.sendMessage(Component.text("Levels reset", NamedTextColor.BLUE));
    }
}
