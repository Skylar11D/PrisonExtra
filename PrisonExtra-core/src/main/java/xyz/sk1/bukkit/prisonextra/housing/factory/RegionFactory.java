package xyz.sk1.bukkit.prisonextra.housing.factory;

import org.bukkit.Location;
import xyz.sk1.bukkit.prisonextra.region.Region;
import xyz.sk1.bukkit.prisonextra.utilities.factory.AbstractRegionFactory;
import xyz.sk1.bukkit.prisonextra.housing.House;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public class RegionFactory extends AbstractRegionFactory {

    @Override
    public House createRegion(Location location, Location location2, String ownerName) {

        return new House(location, location2, ownerName);
    }

}