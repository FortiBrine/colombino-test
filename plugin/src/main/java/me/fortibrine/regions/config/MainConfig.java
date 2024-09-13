package me.fortibrine.regions.config;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class MainConfig {

    private final Plugin plugin;

    @Getter
    private final FileConfiguration config;

    public MainConfig(Plugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();

        plugin.saveDefaultConfig();
    }

    public void reload() {
        plugin.reloadConfig();
    }

}
