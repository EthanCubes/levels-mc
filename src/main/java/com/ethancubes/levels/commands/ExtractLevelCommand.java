package com.ethancubes.levels.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static com.ethancubes.levels.Global.refresh_xp_hearts;

public class ExtractLevelCommand {
    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("extract")
                .executes(context -> extract_one_level(context.getSource()))
                .then(Commands.argument("amount", IntegerArgumentType.integer(1, 20))
                        .executes(context -> {
                            refresh_xp_hearts(context.getSource().getPlayerOrThrow(), false);
                            return extract_levels(context.getSource(), IntegerArgumentType.getInteger(context, "amount"));
                        }))
                .build();
    }

    private static int extract_one_level(CommandSourceStack source) {
        // ensure that a player is typing the command
        if (!(source.getExecutor() instanceof Player player)) {
            source.getSender().sendMessage(Component.text("Only a player can run this command", NamedTextColor.RED));
            return 0;
        }

        int player_level = player.getLevel();

        if (player_level < 10) {
            source.getSender().sendMessage(Component.text("Sorry! Not enough XP levels", NamedTextColor.YELLOW));
            return 0;
        }

        player.setExp(0); // done to prevent overflow and stuff, which allows for abuse of the system (maybe)
        player.setLevel(player_level - 1);

        player.give(ItemStack.of(Material.EXPERIENCE_BOTTLE)); // lets hope this doesn't give an entire stack of bottles
        player.give(ItemStack.of(Material.EXPERIENCE_BOTTLE));
        player.give(ItemStack.of(Material.EXPERIENCE_BOTTLE));

        player.sendMessage(Component.text("Level(s) extracted!", NamedTextColor.GREEN));
        return Command.SINGLE_SUCCESS;
    }

    private static int extract_levels(CommandSourceStack source, int amount) {
        if (!(source.getExecutor() instanceof Player player)) {
            source.getSender().sendMessage(Component.text("Usage: /extract <int>", NamedTextColor.RED));
            return 0;
        }

        int player_level = player.getLevel();

        // Check if the user has at least 10 xp levels currently, and 9 after they extract the xp
        // if player is on level 22, and wants to extract 13, this should work
        if (player_level - amount < 9) {
            source.getSender().sendMessage(Component.text("Sorry! Not enough XP levels", NamedTextColor.YELLOW));
            return 0;
        }

        player.setExp(0); // done to prevent overflow and stuff, which allows for abuse of the system (maybe)
        player.setLevel(player_level - amount);

        for (int i = 0; i < amount; i++) {
            player.give(ItemStack.of(Material.EXPERIENCE_BOTTLE));
            player.give(ItemStack.of(Material.EXPERIENCE_BOTTLE));
            player.give(ItemStack.of(Material.EXPERIENCE_BOTTLE));
        }

        player.sendMessage(Component.text("Level(s) extracted!", NamedTextColor.GREEN));
        return Command.SINGLE_SUCCESS;
    }
}
