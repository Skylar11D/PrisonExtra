package xyz.sk1.bukkit.prisonextra.events.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.events.handler.interact.PrisonMenuHandler;
import xyz.sk1.bukkit.prisonextra.inventory.MenuType;
import xyz.sk1.bukkit.prisonextra.listeners.BaseListener;
import xyz.sk1.bukkit.prisonextra.listeners.PrisonEventHandler;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.player.User;
import xyz.sk1.bukkit.prisonextra.player.UserManager;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

public class InventoryClickListener extends BaseListener {

    private final PrisonEventHandler<InventoryClickEvent> prisonMenu;

    public InventoryClickListener() {
        this.prisonMenu = new PrisonMenuHandler();
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        ItemStack itemStack = event.getCurrentItem();

        if (Utils.getTag(itemStack, "menu") == "background")
            event.setCancelled(true);

        if(event.getClickedInventory().getName() == "               §c§lPrison")
            this.prisonMenu.handle(event);

    }

}
