package xyz.sk1.bukkit.prisonextra.housing;

import org.bukkit.Location;
import xyz.sk1.bukkit.prisonextra.Core;
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

    private double x1;
    private double y1;
    private double z1;
    private double x2;
    private double y2;
    private double z2;

    private String tempName;
    private String wordName;

    Cache<String, Region> cachedRegions;

    @SuppressWarnings("unchecked")
    public HouseManager(){
        this.cachedRegions = Core.getInstance().getLruCacheRegistry().getCache("regions");
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
    public void load() {
        Utils.LOG.info("Loading regions into the cache...");
        /*Connection connection = Core.getInstance().getPDatabase().getConnection();

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM regions");

        if(resultSet.next()){
            tempName = resultSet.getString("owner");
            wordName = resultSet.getString("world");
            x1 = resultSet.getDouble("x1");
            y1 = resultSet.getDouble("y1");
            z1 = resultSet.getDouble("z1");
            x2 = resultSet.getDouble("x2");
            y2 = resultSet.getDouble("y2");
            z2 = resultSet.getDouble("z2");

            Location location1 = new Location(Bukkit.getWorld(wordName), x1, y1, z1);
            Location location2 = new Location(Bukkit.getWorld(wordName), x2, y2, z2);

            cachedRegions.put(tempName, new House(location1, location2, tempName));

        }*/

}

    @Override
    public ManagerType getType() {
        return ManagerType.REGION;
    }
}
