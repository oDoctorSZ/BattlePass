package com.battle.battlepass.battlepass.events;

import com.battle.battlepass.battlepass.BattlePass;
import com.battle.battlepass.battlepass.manager.CreateQuestManager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.w3c.dom.html.HTMLImageElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PSendChatMessage implements Listener {

    List<String> mobs = new ArrayList<>();


    @EventHandler
    public void playerSendMessage(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage();

        if (CreateQuestManager.questNameChat.contains(player)) {

            BattlePass.quest.setQuestName(e.getMessage());
            e.setCancelled(true);

            CreateQuestManager.questNameChat.remove(player);

            CreateQuestManager.openCreateGUI(player);

        } else if (CreateQuestManager.questDescChat.contains(player)) {

            BattlePass.quest.setDesc(e.getMessage());
            e.setCancelled(true);

            CreateQuestManager.questDescChat.remove(player);

            CreateQuestManager.openCreateGUI(player);
        }


        mobs.add(EntityType.values().getClass().getSimpleName());


        for (int i = 0; i < EntityType.values().length; i++) {
            if (CreateQuestManager.killMobList.contains(player)) {
                if (EntityType.values()[i].getEntityClass().getSimpleName().equalsIgnoreCase(message)) {
                    player.sendMessage("E iste");

                    CreateQuestManager.openCreateGUI(player);
                    CreateQuestManager.killMobList.remove(player);
                    break;
                }


                if (!mobs.contains(message)) {

                    player.sendMessage("Não existe!");
                    CreateQuestManager.killMobList.remove(player);


                }
            }

        }


        if (BattlePass.hasOnBlockQuest.contains(player)) {



            BattlePass.hasOnBlockQuest.remove(player);
        }



    }
}
