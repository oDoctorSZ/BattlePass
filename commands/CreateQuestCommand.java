package com.battle.battlepass.battlepass.commands;

import com.battle.battlepass.battlepass.manager.CreateQuestManager;
import com.battle.battlepass.battlepass.references.Mission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

public class CreateQuestCommand extends Mission implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            CreateQuestManager.openCreateGUI(player);

        }
        return false;
    }

}
