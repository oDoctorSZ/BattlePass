package com.battle.battlepass.battlepass.references;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Tier {
    private int exp;
    private List<String> commands = new ArrayList<>();
    private ItemStack item;
    private int amount;

    public Tier(int exp, ItemStack item, List<String> commands, int amount) {
        this.exp = exp;
        this.commands= commands;
        this.item = item;
        this.amount = amount;
    }

    public int getExp() {
        return exp;
    }

}
