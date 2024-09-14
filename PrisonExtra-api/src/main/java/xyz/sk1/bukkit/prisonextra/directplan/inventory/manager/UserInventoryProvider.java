package xyz.sk1.bukkit.prisonextra.directplan.inventory.manager;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.directplan.inventory.UserInventory;

/**
 * @author DirectPlan
 */
public interface UserInventoryProvider {

    UserInventory getUser(Player player);
}
