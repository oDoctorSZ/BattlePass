package com.battle.battlepass.battlepass.events;

import com.battle.battlepass.battlepass.BattlePass;
import com.battle.battlepass.battlepass.references.Mission;
import com.battle.battlepass.battlepass.references.PlayerModel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public class PJoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (!BattlePass.players.containsKey(e.getPlayer().getUniqueId().toString())) {
            String uuid = e.getPlayer().getUniqueId().toString();
            BattlePass.players.put(uuid, new PlayerModel(uuid, 0, "1"));
        }
    }
}
