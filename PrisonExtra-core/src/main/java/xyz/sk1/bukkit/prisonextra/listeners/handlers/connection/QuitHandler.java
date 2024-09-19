package xyz.sk1.bukkit.prisonextra.listeners.handlers.connection;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.listeners.BaseListener;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.player.UserManager;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

public class QuitHandler extends BaseListener {

    @EventHandler
    public void onQuitting(PlayerQuitEvent e){
        Player player = e.getPlayer();
        UserManager userManager = (UserManager) Core.getInstance().getManagerRegistry().getManager(ManagerType.PRISON);

        userManager.release(player);

    }

}
