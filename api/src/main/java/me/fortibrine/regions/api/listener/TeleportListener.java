package me.fortibrine.regions.api.listener;

import lombok.AllArgsConstructor;
import me.fortibrine.regions.api.RegionsApi;
import me.fortibrine.regions.api.event.RegionCrossEvent;
import me.fortibrine.regions.api.region.Region;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;

@AllArgsConstructor
public class TeleportListener implements Listener {

    private final RegionsApi api;
    private final Plugin plugin;

    @EventHandler
    public void onJoin(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();

        Region region = api.getRegionManager().getRegion(to);

        if (region == null) return;
        if (api.getRegionManager().getRegion(from) == region) return;

        RegionCrossEvent regionCrossEvent = new RegionCrossEvent(RegionCrossEvent.Action.JOIN, from, to, player);

        plugin.getServer().getPluginManager().callEvent(regionCrossEvent);

        event.setCancelled(regionCrossEvent.isCancelled());

    }

    @EventHandler
    public void onLeave(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();

        Region region = api.getRegionManager().getRegion(from);

        if (region == null) return;
        if (api.getRegionManager().getRegion(to) == region) return;

        RegionCrossEvent regionCrossEvent = new RegionCrossEvent(RegionCrossEvent.Action.LEAVE, from, to, player);

        plugin.getServer().getPluginManager().callEvent(regionCrossEvent);

        event.setCancelled(regionCrossEvent.isCancelled());
    }

}
