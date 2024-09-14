package xyz.sk1.bukkit.prisonextra.utils.housing;

import org.bukkit.Location;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.internal.cache.Cache;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.region.Region;
import xyz.sk1.bukkit.prisonextra.region.RegionManager;

import java.sql.PreparedStatement;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public final class HouseManager implements RegionManager<House> {

    Cache<String, Region> cachedRegions;

    @SuppressWarnings("unchecked")
    public HouseManager(){
        this.cachedRegions = Core.getInstance().getLruCacheRegistry().getCache("regions");
    }

    @Override
    public void insertRegion(House region) {
        //cachedRegions.put("");
    }

    @Override
    public House getRegionByLocation(Location location) {

        return null;
    }

    @Override
    public void load() throws Exception {

        try(PreparedStatement statement = Core.getInstance().getDatabase().getConnection().prepareStatement("SELECT * FROM region")) {

        }

    }

    @Override
    public ManagerType getType() {
        return ManagerType.REGION;
    }
}
