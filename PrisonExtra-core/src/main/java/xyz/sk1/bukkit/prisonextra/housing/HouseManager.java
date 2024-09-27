package xyz.sk1.bukkit.prisonextra.housing;

import org.bukkit.Location;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.housing.factory.RegionFactory;
import xyz.sk1.bukkit.prisonextra.internal.cache.Cache;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.region.Region;
import xyz.sk1.bukkit.prisonextra.region.RegionManager;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

import java.util.Map;
import java.util.Optional;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public final class HouseManager implements RegionManager<House> {

    private final Cache<String, Region> cachedRegions;
    private RegionFactory regionFactory;


    @SuppressWarnings("unchecked")
    public HouseManager(){
        this.cachedRegions = (Cache<String, Region>) Core.getInstance().getLruCacheRegistry().getCache("regions");
        this.regionFactory = new RegionFactory();
    }

    @Override
    public void insertRegion(House region) {

        cachedRegions.put(region.getOwner(), region);
    }

    @Override
    public Optional<House> getRegionByLocation(Location location) {

        for(Map.Entry<String, Region> house : cachedRegions){

            if (house.getValue().contains(location)){
                return Optional.of((House) house.getValue());
            }

        }

        return Optional.empty();
    }

    @Override
    public House createHouse(Location corner1, Location corner2, String ownerName) {
        return regionFactory.createRegion(corner1, corner2, ownerName);
    }

    @Override
    public void load() {
        Utils.LOG.info("Loading regions into the cache...");

        /*
        * TODO the fetching logic from the database and readingAll to this cache
        * */

    }

    @Override
    public ManagerType getType() {
        return ManagerType.REGION;
    }
}
