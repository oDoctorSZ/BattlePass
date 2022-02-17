package com.battle.battlepass.battlepass.references;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface CommandBase {
    void execute(CommandSender sender, Command command, String[] args);
}
