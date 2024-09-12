package me.fortibrine.regions.api.region;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RegionManager {

    private final List<CuboidRegion> cuboidRegions = new ArrayList<>();

    RegionManager() {

    }

    public @Nullable CuboidRegion createRegion(@NotNull ConfigurationSection section) {
        try {

            String world = section.getString("world");
            String point1 = section.getString("point-1");
            String point2 = section.getString("point-2");

            if (world == null || point1 == null || point2 == null) {
                return null;
            }

            World bukkitWorld = Bukkit.getWorld(world);

            if (bukkitWorld == null) {
                return null;
            }

            Location startPosition = new Location(
                    bukkitWorld,
                    Double.parseDouble(point1.split(";")[0]),
                    Double.parseDouble(point1.split(";")[1]),
                    Double.parseDouble(point1.split(";")[2])
            );

            Location endPosition = new Location(
                    bukkitWorld,
                    Double.parseDouble(point2.split(";")[0]),
                    Double.parseDouble(point2.split(";")[1]),
                    Double.parseDouble(point2.split(";")[2])
            );

            CuboidRegion cuboidRegion = new CuboidRegion(
                    bukkitWorld,
                    startPosition,
                    endPosition
            );

            cuboidRegions.add(cuboidRegion);

            return cuboidRegion;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
