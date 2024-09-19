package xyz.sk1.bukkit.prisonextra.utils.housing.factory;

import org.bukkit.Location;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.region.Region;
import xyz.sk1.bukkit.prisonextra.utilities.factory.AbstractRegionFactory;
import xyz.sk1.bukkit.prisonextra.utils.housing.House;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public class RegionFactory extends AbstractRegionFactory {

    @Override
    public Region createRegion(Location location, Location location2, String ownerName) {

        return new House(location, location2, ownerName);
    }

}