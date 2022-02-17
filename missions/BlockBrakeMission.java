package com.battle.battlepass.battlepass.missions;

import com.battle.battlepass.battlepass.BattlePass;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBrakeMission implements Listener {

    int times = 15;

    @EventHandler
    public void playerBrakeBlock(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();
        int timesToBrake = BattlePass.blocksBrakes.get(block);

        if (BattlePass.blocksBrakes.containsKey(block)) {

            BattlePass.blocksBrakes.values().add(BattlePass.blocksBrakes.get(block) - times);
        }

    }
}
