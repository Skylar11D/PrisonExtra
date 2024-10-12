package xyz.sk1.bukkit.prisonextra.housing.strategy;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.region.strategies.AccessStrategy;
import xyz.sk1.bukkit.prisonextra.housing.House;

public class WhitelistAccessStrategy implements AccessStrategy<House> {

    @Override
    public boolean canAccess(Player player, House region) {



        return false;
    }

}
