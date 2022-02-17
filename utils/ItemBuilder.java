package com.battle.battlepass.battlepass.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {
    private final short damage = 0;

    private ItemStack item;
    private ItemMeta meta;
    private String itemName;
    private String targetInv;

    public ItemBuilder(String displayName, int id, int data) {
        item = new ItemStack(id, 1, damage, (byte) data);
        meta = item.getItemMeta();
        this.itemName = displayName;
        meta.setDisplayName(displayName);
        item.setItemMeta(meta);
    }

    public ItemBuilder(String displayName, int id, int data, String targetInv) {
        item = new ItemStack(id, 1, damage, (byte) data);
        meta = item.getItemMeta();
        this.itemName = displayName;
        meta.setDisplayName(displayName);
        item.setItemMeta(meta);
        this.targetInv = targetInv;
    }

    public void setLore(String... lore) {
        List<String> loreList = new ArrayList<>();
        for (int i = 0; i < lore.length; i++) {
            loreList.add(lore[i].replace("&", "§"));
        }
        meta.setLore(loreList);
        meta.setDisplayName(itemName);
        item.setItemMeta(meta);
    }

    public void setLore(List<String> lore) {
        List<String> newLore = new ArrayList<>();
        lore.forEach(item -> {
            newLore.add(item.replace("&","§"));
        });
        meta.setLore(newLore);
        meta.setDisplayName(itemName);
        item.setItemMeta(meta);
    }

    public ItemStack getItem() {
        return item;
    }

    public String getTarget() {
        return targetInv;
    }
}
