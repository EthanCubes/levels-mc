package com.ethancubes.levels;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class Global {
    public static void refresh_xp_hearts(Player player, boolean is_command) {
        int player_level = player.getLevel();
        int current_player_max_health = (int) Math.floor(player.getMaxHealth());

        if (player_level < 10) {
            if (current_player_max_health != 20) {
                player.resetMaxHealth();
            }
            return;
        }

        int extra_xp = player_level % 10;
        int extra_hearts = (player_level - extra_xp) / 10;
        int player_health = 20 + 2 * extra_hearts;

        if (player_health > 40) {
            player_health = 40;
        }

        if (current_player_max_health != player_health) {
            player.setMaxHealth(player_health); // Deprecated but I seriously have no idea how to make anything better
        }

        // ensures that the user isn't pinged every time they gain xp
        if (is_command) {
            player.sendMessage(Component.text("Refresh successfully completed", NamedTextColor.YELLOW));
        }
    }
}
