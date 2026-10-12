package com.ethancubes.levels.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class RefreshLevelCommand {
    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("refresh")
                .executes(context -> {
                    return refresh_xp_hearts(context.getSource());
                })
                .build();
    }

    private static int refresh_xp_hearts(CommandSourceStack source) {
        if (!(source.getExecutor() instanceof Player player)) {
            source.getSender().sendMessage(Component.text("/refresh can only be used by a player", NamedTextColor.RED));
            return 0;
        }

        player.sendMessage(Component.text("Refresh successfully completed", NamedTextColor.YELLOW));

        int player_level = player.getLevel();

        if (player_level < 10) {
            player.resetMaxHealth();
            return Command.SINGLE_SUCCESS;
        }

        int extra_xp = player_level % 10;
        int extra_hearts = (player_level - extra_xp) / 10;
        int player_health = 20 + 2 * extra_hearts;

        if (player_health > 40) {
            player_health = 40;
        }

        player.setMaxHealth(player_health); // Deprecated but I seriously have no idea how to make anything better

        return Command.SINGLE_SUCCESS;
    }
}
