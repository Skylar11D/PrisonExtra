package xyz.sk1.bukkit.prisonextra.region.strategies;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.region.Region;

public interface AccessStrategy<R extends Region> {

    boolean canAccess(Player player, R region);

}
