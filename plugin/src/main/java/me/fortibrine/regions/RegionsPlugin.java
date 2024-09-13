package me.fortibrine.regions;

import lombok.Getter;
import me.fortibrine.regions.api.RegionsApi;
import me.fortibrine.regions.api.region.Region;
import me.fortibrine.regions.commands.ColombinotestCommand;
import me.fortibrine.regions.config.MainConfig;
import me.fortibrine.regions.listener.CrossRegionListener;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;

public class RegionsPlugin extends JavaPlugin {

    private RegionsApi regionsApi;

    @Getter
    private MainConfig mainConfig;

    @Override
    public void onEnable() {
        regionsApi = new RegionsApi(this);
        mainConfig = new MainConfig(this);

        this.initializeRegions();
        this.initializeCommands();
        this.initializeListeners();
    }

    private void initializeRegions() {
        ConfigurationSection mainRegion = mainConfig.getConfig().getConfigurationSection("cuboid-region");

        if (mainRegion == null) {
            getLogger().warning("Main config wrong");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        Region region = regionsApi.getRegionManager().createCuboidRegion(mainRegion);

        if (region == null) {
            getLogger().warning("Main config wrong");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

    }

    private void initializeCommands() {
        Map.of(
                "colombinotest", new ColombinotestCommand(mainConfig)
        ).forEach((key, value) -> {
            getCommand(key).setExecutor(value);
        });
    }

    private void initializeListeners() {
        List.of(
                new CrossRegionListener(this)
        ).forEach(listener -> {
            getServer().getPluginManager().registerEvents(listener, this);
        });
    }

}
