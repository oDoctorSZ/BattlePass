package com.battle.battlepass.battlepass.utils;

import com.battle.battlepass.battlepass.BattlePass;
import com.battle.battlepass.battlepass.config.ConfigMenus;
import com.battle.battlepass.battlepass.config.ConfigPlayers;
import com.battle.battlepass.battlepass.config.TierConfig;
import com.battle.battlepass.battlepass.references.Mission;
import com.battle.battlepass.battlepass.references.PlayerModel;
import com.battle.battlepass.battlepass.references.Tier;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Utils {

    public static ConfigMenus configMenus;
    public static TierConfig configTier;
    public static ConfigPlayers configPlayers;

    public static void initialize() {
        initializeConfigMenus();
        initializeConfigTier();
        initializeConfigPlayers();
    }

    private static void initializeConfigMenus() {
        configMenus = new ConfigMenus(BattlePass.getPlugin(), "menus.yml");
        configMenus.saveConfig();
        try {
            Set<String> menus = configMenus.getSection("menus").getKeys(false);
            menus.forEach(menu -> {
                if (BattlePass.inventories.get(menu) != null) {
                    Inventory invMain = BattlePass.inventories.get(menu).getInventory();
                    Set<String> items = configMenus.getSection("menus."+menu).getKeys(false);
                    items.forEach(item -> {
                        String name = configMenus.getConfig().getString("menus."+menu+"."+item+".name");
                        int slot = (int) configMenus.getConfig().get("menus."+menu+"."+item+".slot");
                        int idItem = (int) configMenus.getConfig().get("menus."+menu+"."+item+".item.id");
                        int dataItem = (int) configMenus.getConfig().get("menus."+menu+"."+item+".item.data");
                        List<String> lore = configMenus.getConfig().getStringList("menus."+menu+"."+item+".item.lore");
                        String target;

                        if (configMenus.getConfig().get("menus."+menu+"."+item+".target") == null || configMenus.getConfig().get("menus."+menu+"."+item+".target").equals("")) {
                            target = "";
                        } else {
                            target = configMenus.getConfig().getString("menus."+menu+"."+item+".target");
                        }
                        ItemBuilder builder = new ItemBuilder(name.replace("&", "§"), idItem, dataItem, target == null ? "" : target);
                        builder.setLore(lore);
                        BattlePass.itemsActions.put(name.replace("&", "§"), builder);
                        invMain.setItem(slot, builder.getItem());
                    });
                }
            });
            configMenus.saveConfig();
        } catch(Exception err) {}
    }

    private static void initializeConfigTier() {
        configTier = new TierConfig(BattlePass.getPlugin(), "tier.yml");
        configTier.saveConfig();
        try {
            Set<String> tiers = configTier.getSection("tier").getKeys(false);
            tiers.forEach(tier -> {
                int exp = (int) configTier.getConfig().get("tier."+tier+".exp");
                boolean useCommands = configTier.getConfig().getBoolean("tier."+tier+".recompensas.useCommands");
                boolean useItem = configTier.getConfig().getBoolean("tier."+tier+".recompensas.useItem");
                List<String> commands = null;
                String itemName;
                int amount = 0;
                int idItem;
                byte data;
                List<String> lore;
                ItemBuilder builder = null;
                if (useCommands) {
                    commands = configTier.getConfig().getStringList("tier."+tier+".recompensas.commands");
                }
                if (useItem) {
                    itemName = configTier.getConfig().getString("tier."+tier+".recompensas.item.name");
                    amount = configTier.getConfig().getInt("tier."+tier+".recompensas.item.amount");
                    idItem = configTier.getConfig().getInt("tier."+tier+".recompensas.item.id");
                    data = (byte) configTier.getConfig().getInt("tier."+tier+".recompensas.item.data");
                    lore = configTier.getConfig().getStringList("tier."+tier+".recompensas.item.lore");
                    builder = new ItemBuilder(itemName, idItem, data, "");
                    builder.setLore(lore);
                }

                BattlePass.tiers.put(tier, new Tier(exp, builder == null ? null : builder.getItem(), commands, amount));
            });
            InventoryBuilder invTiers = new InventoryBuilder(9*5, "Tier");
            BattlePass.tiers.forEach((nameTier, tier) -> {
                int data = 7;
                invTiers.getInventory().addItem(new ItemBuilder("Tier "+nameTier, 160, (byte) data).getItem());
            });
            BattlePass.inventories.put("Tier", invTiers);
        } catch (Exception err) {}
    }

    private static void initializeConfigPlayers() {
        configPlayers = new ConfigPlayers(BattlePass.getPlugin(), "players.yml");
        configPlayers.saveConfig();
        try {
            Set<String> playersUUID = configPlayers.getSection("players").getKeys(false);
            playersUUID.forEach(uuid -> {
                int exp =configPlayers.getConfig().getInt("players."+uuid+".exp");
                String tier =configPlayers.getConfig().getString("players."+uuid+".tier");
                BattlePass.players.put(uuid, new PlayerModel(uuid, exp, tier));
            });
        } catch (Exception err) {}
    }

}
