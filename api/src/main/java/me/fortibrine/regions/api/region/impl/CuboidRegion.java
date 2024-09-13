package me.fortibrine.regions.api.region.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.fortibrine.regions.api.region.Region;
import org.bukkit.Location;
import org.bukkit.World;

@AllArgsConstructor
@Getter
public class CuboidRegion implements Region {

    private final World world;
    private final Location startPoint;
    private final Location endPoint;

    @Override
    public boolean contains(Location location) {
        double x1 = Math.min(
                startPoint.getX(),
                endPoint.getX()
        );
        double x2 = Math.max(
                startPoint.getX(),
                endPoint.getX()
        );

        double y1 = Math.min(
                startPoint.getY(),
                endPoint.getY()
        );
        double y2 = Math.max(
                startPoint.getY(),
                endPoint.getY()
        );

        double z1 = Math.min(
                startPoint.getZ(),
                endPoint.getZ()
        );
        double z2 = Math.max(
                startPoint.getZ(),
                endPoint.getZ()
        );

        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();

        return x1 <= x && x <= x2 &&
                y1 <= y && y <= y2 &&
                z1 <= z && z <= z2;
    }

}
