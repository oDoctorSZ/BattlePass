package com.battle.battlepass.battlepass.manager;

import com.battle.battlepass.battlepass.BattlePass;
import com.battle.battlepass.battlepass.enums.MissionType;
import com.battle.battlepass.battlepass.references.Mission;
import com.battle.battlepass.battlepass.utils.InventoryBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CreateQuestManager extends Mission {

    public static List<Player> questNameChat = new ArrayList<>();
    public static List<Player> questDescChat = new ArrayList<>();


    public static List<Player> killMobList = new ArrayList<>();

    public static List<EntityType> entityTypeList;

    private MissionType missionType;
    private String questName;
    private String desc;

    public CreateQuestManager(MissionType missionType, String questName, String desc) {
        this.missionType = missionType;
        this.questName = questName;
        this.desc = desc;
    }

    public CreateQuestManager() {}

    public MissionType getMissionType() {
        return missionType;
    }

    public void setMissionType(MissionType missionType) {
        this.missionType = missionType;
    }

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static void openCreateGUI(Player player)  {

        InventoryBuilder inv = new InventoryBuilder(9*3, "CreateQuest");

        if (BattlePass.killTarget.containsKey(player)) {
            createItem(inv, Material.STONE, 11, 1 , "Type Quest", Arrays.asList("Quest Type: Kill", "\nCurrent Mob: " + BattlePass.killTarget.get(player)));
        } else {
            createItem(inv, Material.STONE, 11, 1 , "Type Quest", null);
        }


        if (BattlePass.quest.questName != null) {
            createItem(inv, Material.BOOK, 13, 1 ,"Quest Name", Collections.singletonList("Quest Name: " + BattlePass.quest.questName));
        } else {
            createItem(inv, Material.BOOK, 13, 1 ,"Quest Name", null);
        }

        if (BattlePass.quest.desc != null) {
            createItem(inv, Material.ANVIL, 15, 1 , "Quest Description", Collections.singletonList("Quest Description: " + BattlePass.quest.desc));
        } else {
            createItem(inv, Material.ANVIL, 15, 1 , "Quest Description", null);
        }

        player.openInventory(inv.getInventory());

    }

    public static void openTypeMissionGUI(Player player)  {

        InventoryBuilder inv = new InventoryBuilder(9*3, "Select a Quest Type");

        createItem(inv, Material.STONE, 11, 1 , "Kill Mob", null);
        createItem(inv, Material.BOOK, 13, 1 ,"Brake Block", null);
        createItem(inv, Material.ANVIL, 15, 1 , "Pickup Block", null);

        player.openInventory(inv.getInventory());

    }

    public static void openMobListGui(Player player) {

        InventoryBuilder inv = new InventoryBuilder(9*6 - 1, "Mob List");
        InventoryBuilder inv2 = new InventoryBuilder(9*6, "Mob List");

        for (EntityType mob : EntityType.values()) {
            if (mob.isAlive()) {
                for (int i = 0; i < EntityType.values().length; i++) {

                    createItem(inv, Material.BOOK, i, 1, mob.getEntityClass().getSimpleName(), null);

                }
            }
        }

        player.openInventory(inv.getInventory());
    }


    public static void brakeBlockGui(Player player) {

        InventoryBuilder inv = new InventoryBuilder(9*3, "Block Quest");

        createItem(inv, Material.STONE, 13, 1, "Select Block", null);
        createItem(inv, Material.BOOK, 16, 1, "Select Quantity", null);

        player.openInventory(inv.getInventory());
    }



    private static ItemStack createItem(InventoryBuilder inv, Material item, int slot, int amount, String itemName, List<String> lore) {

        ItemStack itemStack = new ItemStack(item, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(itemName);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        inv.getInventory().setItem(slot, itemStack);


        return itemStack;
    }

}
