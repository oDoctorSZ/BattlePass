package com.battle.battlepass.battlepass;

import com.battle.battlepass.battlepass.commands.CreateQuestCommand;
import com.battle.battlepass.battlepass.events.PInventoryClick;
import com.battle.battlepass.battlepass.events.PJoinEvent;
import com.battle.battlepass.battlepass.events.PSendChatMessage;
import com.battle.battlepass.battlepass.manager.CommandManager;
import com.battle.battlepass.battlepass.manager.CreateQuestManager;
import com.battle.battlepass.battlepass.missions.KillEnemyMission;
import com.battle.battlepass.battlepass.references.Mission;
import com.battle.battlepass.battlepass.references.PlayerModel;
import com.battle.battlepass.battlepass.references.Tier;
import com.battle.battlepass.battlepass.utils.InventoryBuilder;
import com.battle.battlepass.battlepass.utils.ItemBuilder;
import com.battle.battlepass.battlepass.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class BattlePass extends JavaPlugin {

    public static CreateQuestManager quest = new CreateQuestManager();

    public static HashMap<String, InventoryBuilder> inventories = new HashMap<>();
    public static HashMap<String, ItemBuilder> itemsActions= new HashMap<>();
    public static HashMap<String, Tier> tiers = new HashMap<>();
    public static HashMap<String, PlayerModel> players = new HashMap<>();
    private static JavaPlugin plugin;

    public static HashMap<Player, String> killTarget = new HashMap<>();
    public static HashMap<Block, Integer> blocksBrakes = new HashMap<>();
    public static List<Player> hasOnBlockQuest = new ArrayList<>();

    public static List<CreateQuestManager> registerQuest = new ArrayList<>();


    public static void registerInventoriesBase() {
        BattlePass.inventories.put("BattlePass", new InventoryBuilder(9*3, "BattlePass"));
        BattlePass.inventories.put("Missions", new InventoryBuilder(9*3, "Missions"));
    }

    @Override
    public void onEnable() {
        plugin = this;
        getCommand("bp").setExecutor(new CommandManager());
        getCommand("cq").setExecutor(new CreateQuestCommand());

        Bukkit.getPluginManager().registerEvents(new PInventoryClick(), this);
        Bukkit.getPluginManager().registerEvents(new PJoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new KillEnemyMission(), this);
        Bukkit.getPluginManager().registerEvents(new PSendChatMessage(), this);


        registerInventoriesBase();
        Utils.initialize();
    }

    @Override
    public void onDisable() {
        BattlePass.players.forEach((uuid, model) ->{
            Bukkit.getConsoleSender().sendMessage(uuid);
            Utils.configPlayers.getConfig().set("players."+uuid+".exp", model.getExp());
            Utils.configPlayers.getConfig().set("players."+uuid+".tier", model.getTier());
        });
        Utils.configPlayers.saveConfig();
    }

    public static JavaPlugin getPlugin() {return plugin;}
}
