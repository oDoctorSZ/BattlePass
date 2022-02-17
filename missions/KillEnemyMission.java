package com.battle.battlepass.battlepass.missions;

import com.battle.battlepass.battlepass.BattlePass;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class KillEnemyMission implements Listener {

    int times = 5;


    @EventHandler
    public void playerKillTarget(EntityDeathEvent e) {
        Player player = e.getEntity().getKiller();
        Entity target = e.getEntity();

        if (BattlePass.killTarget.containsKey(player)) {
            if (target.getName().equalsIgnoreCase(BattlePass.killTarget.get(player))) {
                times--;

                if (times == 0) {
                    BattlePass.killTarget.remove(player);

                    player.sendMessage("Voce completou a quest!");
                }
            }
        }


    }


}
