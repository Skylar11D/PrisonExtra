package xyz.sk1.bukkit.prisonextra.utils.housing.strategy;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.region.strategies.AccessStrategy;
import xyz.sk1.bukkit.prisonextra.utils.housing.House;

public class WhitelistAccessStrategy implements AccessStrategy<House> {

    @Override
    public boolean canAccess(Player player, House region) {

        //the logic of looping the regions for checking if the player is accessible

        return false;
    }

}
