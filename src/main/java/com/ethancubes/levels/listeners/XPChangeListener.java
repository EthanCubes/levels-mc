package com.ethancubes.levels.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;

import static com.ethancubes.levels.Global.refresh_xp_hearts;

public class XPChangeListener implements Listener {
    @EventHandler
    public void on_xp_change(PlayerExpChangeEvent event) {
        refresh_xp_hearts(event.getPlayer(), false);
    }

    @EventHandler
    public void on_item_enchant(EnchantItemEvent event) { refresh_xp_hearts(event.getEnchanter(), false); }

    // Can't wait for an edge case to pop up that completely breaks the code because I decided to cast any entity as a player
    @EventHandler
    public void on_inventory_click(InventoryClickEvent event) { refresh_xp_hearts((Player) event.getWhoClicked(), false);}

    @EventHandler
    public void on_inventory_drag(InventoryDragEvent event) { refresh_xp_hearts((Player) event.getWhoClicked(), false);}
}
