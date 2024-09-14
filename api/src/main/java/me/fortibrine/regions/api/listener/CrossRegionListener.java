package me.fortibrine.regions.api.listener;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.RequiredArgsConstructor;
import me.fortibrine.regions.api.event.RegionCrossEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class CrossRegionListener implements Listener {

    private final Cache<UUID, Boolean> inCooldown = CacheBuilder.newBuilder()
            .expireAfterAccess(3, TimeUnit.SECONDS)
            .build();
    private final Plugin plugin;

    @EventHandler(priority = EventPriority.MONITOR)
    public void onCrossRegion(RegionCrossEvent event) {

        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();

        if (Boolean.TRUE.equals(inCooldown.getIfPresent(player.getUniqueId()))) {

            String world = from.getWorld().getName();

            plugin.getLogger().info(new StringBuilder()
                    .append("Player ")
                    .append(player.getName())
                    .append(" intersected the region from (")
                    .append(world).append(", ").append(from.getBlockX()).append(", ").append(from.getBlockY()).append(", ").append(from.getBlockZ())
                    .append(") to (")
                    .append(world).append(", ").append(to.getBlockX()).append(", ").append(to.getBlockY()).append(", ").append(to.getBlockZ())
                    .append(")")
                    .toString());

            inCooldown.put(player.getUniqueId(), true);
        }

    }

}
