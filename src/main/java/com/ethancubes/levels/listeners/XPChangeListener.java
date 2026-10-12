package com.ethancubes.levels.listeners;

// import org.bukkit.attribute.AttributeInstance;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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

        int player_health = 20;
        for (int i = player_level; i > 10; i -= 10) {
            player_health += 2;
        }

        player.setMaxHealth(player_health); // Deprecated but I seriously have no idea how to make anything better
        player.sendMessage(Component.text("Level changes!", NamedTextColor.BLUE));
    }
}
