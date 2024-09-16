package xyz.sk1.bukkit.prisonextra.region;

import org.bukkit.Location;
import xyz.sk1.bukkit.prisonextra.manager.Manager;

import java.util.Optional;

public interface RegionManager<R extends Region> extends Manager {

    /**
     * Add the specified region to the local cache
     * then inserted to the database upon every shutdown
     * @param region
     * @see xyz.sk1.bukkit.prisonextra.internal.cache.Cache
     */
    void insertRegion(R region);

    /**
     * Get the location of the region from the cache
     *
     * @param location
     */
    Optional<R> getRegionByLocation(Location location);

}
