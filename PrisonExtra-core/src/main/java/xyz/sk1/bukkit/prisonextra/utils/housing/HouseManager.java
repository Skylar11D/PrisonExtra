package xyz.sk1.bukkit.prisonextra.utils.housing;

import org.bukkit.Location;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.region.Region;
import xyz.sk1.bukkit.prisonextra.region.RegionManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public final class HouseManager implements RegionManager {

    public HouseManager(){

    }

    @Override
    public void insertRegion(Region region) {

    }

    @Override
    public void getRegionByLocation(Location location) {

    }

    @Override
    public void load() throws SQLException {

        PreparedStatement statement = Core.getInstance().getDatabase().getConnection().prepareStatement("SELECT * FROM region");

    }

    @Override
    public ManagerType getType() {
        return ManagerType.REGION;
    }
}
