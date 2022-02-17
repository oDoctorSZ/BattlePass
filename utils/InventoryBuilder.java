package com.battle.battlepass.battlepass.utils;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class InventoryBuilder {

    private Inventory inv;

    public InventoryBuilder(int size, String name) {
        this.inv = Bukkit.createInventory(null, size, name);
    }

    public Inventory getInventory() {
        return inv;
    }
}
