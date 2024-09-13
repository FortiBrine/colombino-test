package me.fortibrine.regions.api.listener;

import lombok.RequiredArgsConstructor;
import me.fortibrine.regions.api.event.RegionCrossEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class CrossRegionListener implements Listener {

    private final Map<UUID, Long> cooldown = new HashMap<>();
    private final Plugin plugin;

    @EventHandler(priority = EventPriority.MONITOR)
    public void onCrossRegion(RegionCrossEvent event) {

        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();

        if (cooldown.getOrDefault(player.getUniqueId(), 0L) < System.currentTimeMillis()) {

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

            cooldown.put(player.getUniqueId(), System.currentTimeMillis() + 3000L);
        }
    }

}
