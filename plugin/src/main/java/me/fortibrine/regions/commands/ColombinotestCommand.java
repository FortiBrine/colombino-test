package me.fortibrine.regions.commands;

import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class ColombinotestCommand implements CommandExecutor {

    private final Plugin plugin;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cnd, @NotNull String label, @NotNull String[] args) {

        if (args.length < 1 || args[0].equalsIgnoreCase("reload")) return true;
        if (!sender.hasPermission("colombinotest.reload")) return true;

        plugin.reloadConfig();

        return true;
    }

}
