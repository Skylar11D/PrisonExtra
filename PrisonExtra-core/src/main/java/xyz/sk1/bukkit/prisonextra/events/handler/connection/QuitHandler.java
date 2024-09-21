package xyz.sk1.bukkit.prisonextra.events.handler.connection;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.listeners.PrisonEventHandler;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.player.UserManager;

public class QuitHandler implements PrisonEventHandler<PlayerQuitEvent> {

    @EventHandler
    public void handle(PlayerQuitEvent e){
        Player player = e.getPlayer();
        UserManager userManager = (UserManager) Core.getInstance().getManagerRegistry().getManager(ManagerType.PRISON);

        userManager.release(player);

    }

}
