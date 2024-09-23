package xyz.sk1.bukkit.prisonextra.events.handler.block;

import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.listeners.PrisonEventHandler;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.player.User;
import xyz.sk1.bukkit.prisonextra.player.UserManager;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

public class WandSelectionHandler implements PrisonEventHandler<PlayerInteractEvent> {

    @Override
    public void handle(PlayerInteractEvent event) {
        UserManager userManager = (UserManager) Core.getInstance().getManagerRegistry().getManager(ManagerType.PRISON);
        User user = userManager.get(event.getPlayer());

        if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
            user.setCorner1(event.getClickedBlock().getLocation());
            user.getHandle().sendMessage(Utils.colorize("&6Position &e#1 &6has been marked."));
        }

        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            user.setCorner2(event.getClickedBlock().getLocation());
            user.getHandle().sendMessage(Utils.colorize("&6Position &e#1 &6has been marked."));
        }

    }
}
