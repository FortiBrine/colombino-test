package me.fortibrine.regions.commands;

import lombok.RequiredArgsConstructor;
import me.fortibrine.regions.config.MainConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class ColombinotestCommand implements CommandExecutor {

    private final MainConfig mainConfig;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cnd, @NotNull String label, @NotNull String[] args) {

        if (args.length < 1 || args[0].equalsIgnoreCase("reload")) return true;

        mainConfig.reload();

        return true;
    }

}
