package xyz.sk1.bukkit.prisonextra.events.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.events.handler.connection.UserManagementHandler;
import xyz.sk1.bukkit.prisonextra.inventory.MenuType;
import xyz.sk1.bukkit.prisonextra.listeners.BaseListener;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.player.User;
import xyz.sk1.bukkit.prisonextra.player.UserManager;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

public class InventoryClickListener extends BaseListener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        ItemStack itemStack = event.getCurrentItem();
        Player player = (Player) event.getWhoClicked();
        UserManager userManager = (UserManager)Core.getInstance().
                getManagerRegistry().getManager(ManagerType.PRISON);

        User user = userManager.get(player);

        if(event.getClickedInventory().getName() == "               §c§lPrison")
            event.setCancelled(true);

        if (Utils.getTag(itemStack, "menu") == "background")
            event.setCancelled(true);

        if (itemStack.getItemMeta().getDisplayName() == "§a§lHousing" && itemStack.getType() == Material.WOOD_DOOR){
            event.setCancelled(true);
            user.openMenu(MenuType.HOUSING);
        }

        if (itemStack.getItemMeta().getDisplayName() == "§e§lMinion" && itemStack.getType() == Material.STONE_PICKAXE){
            event.setCancelled(true);
            user.openMenu(MenuType.MINIONS);
        }

        if (itemStack.getItemMeta().getDisplayName() == "§d§lCosmetics" && itemStack.getType() == Material.BLAZE_POWDER){
            event.setCancelled(true);
            user.openMenu(MenuType.COSMETICS);
        }

    }

}
