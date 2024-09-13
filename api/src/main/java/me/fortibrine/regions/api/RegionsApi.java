package me.fortibrine.regions.api;

import lombok.Getter;
import me.fortibrine.regions.api.listener.CrossRegionListener;
import me.fortibrine.regions.api.listener.MoveListener;
import me.fortibrine.regions.api.listener.TeleportListener;
import me.fortibrine.regions.api.region.manager.RegionManager;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.List;

public class RegionsApi {

    private final Plugin plugin;

    @Getter
    private final RegionManager regionManager;

    public RegionsApi(Plugin plugin) {
        this.plugin = plugin;
        this.regionManager = new RegionManager();

        Server server = plugin.getServer();
        PluginManager pluginManager = server.getPluginManager();

        List.of(
                new MoveListener(this, plugin),
                new TeleportListener(this, plugin),
                new CrossRegionListener(plugin)
        ).forEach(listener -> pluginManager.registerEvents(listener, plugin));
    }

}
