package xyz.sk1.bukkit.prisonextra.utils.housing;

import lombok.Data;
import org.bukkit.Location;
import xyz.sk1.bukkit.prisonextra.region.Region;

public class House extends Region {

    public House(Location position1, Location position2) {
        super(position1, position2);
    }

    public boolean contains(Location location){

        return location.getX() > getMinX() && location.getY() > getMinY() && location.getZ() < getMinZ() &&
                location.getX() > getMaxX() && location.getY() > getMaxY() && location.getZ() > getMaxZ();
    }

}
