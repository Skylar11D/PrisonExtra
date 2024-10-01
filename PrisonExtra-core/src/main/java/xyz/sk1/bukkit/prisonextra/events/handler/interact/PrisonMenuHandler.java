package xyz.sk1.bukkit.prisonextra.events.handler.interact;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.inventory.MenuType;
import xyz.sk1.bukkit.prisonextra.listeners.PrisonEventHandler;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.player.User;
import xyz.sk1.bukkit.prisonextra.player.UserManager;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

public class PrisonMenuHandler implements PrisonEventHandler<InventoryClickEvent> {


    @Override
    public void handle(InventoryClickEvent event) {
        ItemStack itemStack = event.getCurrentItem();
        Player player = (Player) event.getWhoClicked();
        UserManager userManager = (UserManager) Core.getInstance().
                getManagerRegistry().getType(ManagerType.PRISON);

        User user = userManager.get(player);

        if (Utils.getTag(itemStack, "menu") == "housing"){
            event.setCancelled(true);
            user.openMenu(MenuType.HOUSING);
        }

        if (Utils.getTag(itemStack, "menu") == "minion"){
            event.setCancelled(true);
            user.openMenu(MenuType.MINIONS);
        }

        if (Utils.getTag(itemStack, "menu") == "cosmetics"){
            event.setCancelled(true);
            user.openMenu(MenuType.COSMETICS);
        }

    }
}
