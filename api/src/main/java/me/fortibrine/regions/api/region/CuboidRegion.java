package me.fortibrine.regions.api.region;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;

@Getter
public class CuboidRegion {

    private final World world;
    private final Location startPoint;
    private final Location endPoint;

    public CuboidRegion(World world, Location startPoint, Location endPoint) {
        this.world = world;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

}
