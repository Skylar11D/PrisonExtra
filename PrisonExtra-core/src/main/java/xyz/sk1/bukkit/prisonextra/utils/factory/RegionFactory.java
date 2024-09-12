package xyz.sk1.bukkit.prisonextra.utils.factory;

import org.bukkit.Location;
import xyz.sk1.bukkit.prisonextra.region.Region;
import xyz.sk1.bukkit.prisonextra.utilities.factory.AbstractRegionFactory;
import xyz.sk1.bukkit.prisonextra.utils.housing.House;

public class RegionFactory extends AbstractRegionFactory {


    @Override
    public Region createRegion(Location location, Location location2) {
        return new House(location, location2);
    }

}