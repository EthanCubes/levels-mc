package com.ethancubes.levels.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class XPChangeListener implements Listener {
    @EventHandler
    public void on_xp_change(PlayerExpChangeEvent event) {
        Player player = event.getPlayer();
        int player_level = player.getLevel();

        if (player_level < 10) {
            return;
        }

        int extra_xp = player_level % 10;
        int extra_hearts = (player_level - extra_xp) / 10;
        int player_health = 20 + 2 * extra_hearts;

        player.setMaxHealth(player_health); // Deprecated but I seriously have no idea how to make anything better
    }
}
