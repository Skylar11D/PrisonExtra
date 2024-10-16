package xyz.sk1.bukkit.prisonextra.housing;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.region.RegionState;
import xyz.sk1.bukkit.prisonextra.region.features.Accessable;
import xyz.sk1.bukkit.prisonextra.region.strategies.AccessStrategy;
import xyz.sk1.bukkit.prisonextra.region.Region;
import xyz.sk1.bukkit.prisonextra.housing.strategy.WhitelistAccessStrategy;

import java.util.List;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public final class House extends Region implements Accessable {

    @Getter
    private List<String> deniedPlayers;
    private String Owner;
    private
    @Getter
    boolean whitelist;
    private final AccessStrategy accessStrategy;

    public House(Location position1, Location position2, String owner) {
        super(position1, position2, owner, RegionState.BOUGHT);
        this.accessStrategy = new WhitelistAccessStrategy();
    }

    @Override
    public boolean contains(Location location){

        return location.getX() > getMinX() && location.getY() > getMinY() && location.getZ() < getMinZ() &&
                location.getX() > getMaxX() && location.getY() > getMaxY() && location.getZ() > getMaxZ();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void canEnter(Player player){
        this.accessStrategy.canAccess(player, this);
    }

}
