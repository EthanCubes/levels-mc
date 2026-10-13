package com.ethancubes.levels;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class Global {
    public static void refresh_xp_hearts(Player player, boolean is_command) {
        int player_level = player.getLevel();

        if (player_level < 10) {
            player.resetMaxHealth();
            return;
        }

        int extra_xp = player_level % 10;
        int extra_hearts = (player_level - extra_xp) / 10;
        int player_health = 20 + 2 * extra_hearts;

        if (player_health > 40) {
            player_health = 40;
        }

        player.setMaxHealth(player_health); // Deprecated but I seriously have no idea how to make anything better

        // ensures that the user isn't pinged every time they gain xp
        if (is_command) {
            player.sendMessage(Component.text("Refresh successfully completed", NamedTextColor.YELLOW));
        }

        return;
    }
}
