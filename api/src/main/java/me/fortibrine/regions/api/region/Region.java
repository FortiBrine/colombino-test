package me.fortibrine.regions.api.region;

import org.bukkit.Location;
import org.bukkit.World;

public interface Region {

    World getWorld();
    Location getStartPoint();
    Location getEndPoint();
    boolean contains(Location location);

}
