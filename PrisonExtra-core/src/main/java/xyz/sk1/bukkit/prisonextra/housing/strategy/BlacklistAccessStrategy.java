package xyz.sk1.bukkit.prisonextra.housing.strategy;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.region.Region;
import xyz.sk1.bukkit.prisonextra.region.strategies.AccessStrategy;

public class BlacklistAccessStrategy implements AccessStrategy<Region> {
    @Override
    public boolean canAccess(Player player, Region region) {

        //TODO this also needs to get finished or you are not a real saitama

        return false;
    }
}
