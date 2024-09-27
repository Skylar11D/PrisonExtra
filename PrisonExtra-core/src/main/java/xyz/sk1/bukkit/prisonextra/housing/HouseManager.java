package xyz.sk1.bukkit.prisonextra.housing;

import org.bukkit.Location;
import sun.awt.image.ImageWatched;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.housing.factory.RegionFactory;
import xyz.sk1.bukkit.prisonextra.internal.cache.Cache;
import xyz.sk1.bukkit.prisonextra.internal.storage.repository.Repository;
import xyz.sk1.bukkit.prisonextra.internal.storage.repository.RepositoryType;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.region.Region;
import xyz.sk1.bukkit.prisonextra.region.RegionManager;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

import java.util.*;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public final class HouseManager implements RegionManager<House> {

    //private final Cache<String, Region> cachedRegions;
    private List<House> cachedRegions;
    private RegionFactory regionFactory;
    private Repository<Region> repository;


    @SuppressWarnings("unchecked")
    public HouseManager(){
        //this.cachedRegions = (Cache<String, Region>) Core.getInstance().getLruCacheRegistry().getCache("regions");
        this.regionFactory = new RegionFactory();
        this.repository = (Repository<Region>) Core.getInstance().getRepositoryRegistry().getType(RepositoryType.REGIONS);
        this.cachedRegions = new LinkedList<>();

    }

    @Override
    public void insertRegion(House region) {

        cachedRegions.add(region);
    }

    @Override
    public Optional<House> getRegionByLocation(Location location) {

        for(House house : cachedRegions){

            if (house.contains(location)){
                return Optional.of(house);
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

        List<String> list = new ArrayList<>();

    }

    @Override
    public ManagerType getType() {
        return ManagerType.REGION;
    }
}
