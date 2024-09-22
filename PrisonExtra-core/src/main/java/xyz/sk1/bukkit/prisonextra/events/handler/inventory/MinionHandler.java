package xyz.sk1.bukkit.prisonextra.events.handler.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.inventory.MenuType;
import xyz.sk1.bukkit.prisonextra.listeners.PrisonEventHandler;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.player.User;
import xyz.sk1.bukkit.prisonextra.player.UserManager;

public class MinionHandler implements PrisonEventHandler<InventoryClickEvent> {

    @Override
    public void handle(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        User user = ((UserManager) Core.getInstance().getManagerRegistry().getManager(ManagerType.PRISON)).get(player);

        user.openMenu(MenuType.MINIONS);
    }

}
