package com.battle.battlepass.battlepass.manager;

import com.battle.battlepass.battlepass.BattlePass;
import com.battle.battlepass.battlepass.commands.BPCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandManager implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("bp")) {
            if (args.length == 0) {
                new BPCommand().execute(sender, command, args);
            }
        }


        return false;
    }
}
