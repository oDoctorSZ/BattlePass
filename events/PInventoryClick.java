package com.battle.battlepass.battlepass.events;

import com.battle.battlepass.battlepass.BattlePass;
import com.battle.battlepass.battlepass.manager.CreateQuestManager;
import com.battle.battlepass.battlepass.references.Tier;
import com.battle.battlepass.battlepass.utils.InventoryBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PInventoryClick implements Listener {
    @EventHandler
    public void InventoryClick(InventoryClickEvent e) {
        if (BattlePass.inventories.get(e.getInventory().getName()) != null) {
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            if (e.getCurrentItem().getTypeId() == 0) {
                return;
            }
            String itemName = e.getCurrentItem().getItemMeta().getDisplayName();
            if (BattlePass.itemsActions.containsKey(itemName)) {
                String target = BattlePass.itemsActions.get(itemName).getTarget();
                if (target.equalsIgnoreCase("")) {
                    return;
                }
                if (target.equalsIgnoreCase("tier")) {
                    BattlePass.inventories.get(target).getInventory().forEach(item -> {
                        if (item == null) return;
                        String tierName = item.getItemMeta().getDisplayName().replace("Tier ", "");

                        BattlePass.players.forEach((uuid, model) -> {
                            Tier tier = BattlePass.tiers.get(tierName);
                            int expTier = tier.getExp();

                            List<String> lore = new ArrayList<>();
                            ItemMeta meta = item.getItemMeta();

                            if (expTier > model.getExp()) {
                                lore.add("§4Você precisa de §b" + expTier + "§4 para este Tier");
                                meta.setDisplayName(item.getItemMeta().getDisplayName());
                                meta.setLore(lore);
                                item.setItemMeta(meta);
                            } else {
                                lore.add("§bVocê possui o exp necessário para subir de tier");
                                meta.setDisplayName(item.getItemMeta().getDisplayName());
                                meta.setLore(lore);
                                item.setItemMeta(meta);
                            }
                        });
                    });
                    player.openInventory(BattlePass.inventories.get(target).getInventory());
                    return;
                }
                player.openInventory(BattlePass.inventories.get(target).getInventory());
            }
        }

        if (e.getInventory().getName().equals("CreateQuest")) {
            e.setCancelled(true);

            Player player = (Player) e.getWhoClicked();

            switch (e.getCurrentItem().getType()) {
                case STONE:

                    CreateQuestManager.openTypeMissionGUI(player);

                    break;
                case BOOK:

                    CreateQuestManager.questNameChat.add(player);

                    player.closeInventory();

                    player.sendMessage("Digite o nome da quest!");

                    break;
                case ANVIL:

                    CreateQuestManager.questDescChat.add(player);

                    player.closeInventory();

                    player.sendMessage("Digite a descrição da Missão!");

                    break;

            }
        }

        if (e.getInventory().getName().equals("Select a Quest Type")) {
            e.setCancelled(true);

            Player player = (Player) e.getWhoClicked();

            switch (e.getCurrentItem().getType()) {
                case STONE:

                    player.closeInventory();

                    CreateQuestManager.killMobList.add(player);

                    player.sendMessage("Qual o mob?");


                    break;
                case BOOK:

                    player.sendMessage("Digite o nome da quest!");

                    break;
                case ANVIL:

                    player.sendMessage("Digite a descrição da Missão!");

                    break;

            }
        }


        if (e.getInventory().getName().equals("Mob List")) {
            e.setCancelled(true);

            Player player = (Player) e.getWhoClicked();

            BattlePass.killTarget.put(player, e.getCurrentItem().getItemMeta().getDisplayName());

            CreateQuestManager.openCreateGUI(player);
        }

        if (e.getInventory().getName().equals("Block Quest")) {
            e.setCancelled(true);

            Player player = (Player) e.getWhoClicked();

            switch (e.getCurrentItem().getType()) {
                case STONE:

                    player.sendMessage("Qual é o Tipo do Block?");

                    BattlePass.hasOnBlockQuest.add(player);
                    player.closeInventory();

                    break;
                case BOOK:

                    player.sendMessage("Quantos devem ser quebrados?");

                    BattlePass.hasOnBlockQuest.add(player);
                    player.closeInventory();

                    break;

            }
        }
    }
}
