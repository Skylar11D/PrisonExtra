package xyz.sk1.bukkit.prisonextra.events.handler.connection;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.listeners.PrisonEventHandler;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.player.UserManager;

public class UserManagementHandler implements PrisonEventHandler<PlayerJoinEvent> {

    @EventHandler
    public void handle(PlayerJoinEvent e){
        Player player = e.getPlayer();
        UserManager userManager = (UserManager) Core.getInstance().getManagerRegistry().getManager(ManagerType.PRISON);

        userManager.imprison(player);
    }

}
