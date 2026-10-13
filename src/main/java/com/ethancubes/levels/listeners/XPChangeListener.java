package com.ethancubes.levels.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

import static com.ethancubes.levels.Global.refresh_xp_hearts;

public class XPChangeListener implements Listener {
    @EventHandler
    public void on_xp_change(PlayerExpChangeEvent event) {
        refresh_xp_hearts(event.getPlayer(), false);
    }
}
