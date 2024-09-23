package xyz.sk1.bukkit.prisonextra.utilities.factory;

import org.bukkit.Location;
import xyz.sk1.bukkit.prisonextra.region.Region;

public abstract class AbstractRegionFactory {

    public abstract <R extends Region> R createRegion(Location position1, Location position2, String ownerName);

}