package com.ethancubes.levels.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

import static com.ethancubes.levels.Global.refresh_xp_hearts;

public class RefreshLevelCommand {
    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("refresh")
                .executes(context -> {
                    if (!(context.getSource().getExecutor() instanceof Player player)) {
                        context.getSource().getSender().sendMessage(Component.text("/refresh can only be used by a player", NamedTextColor.RED));
                        return 0;
                    }
                    refresh_xp_hearts(player);
                    return Command.SINGLE_SUCCESS;
                })
                .build();
    }
}
