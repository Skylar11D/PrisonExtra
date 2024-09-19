package xyz.sk1.bukkit.prisonextra.listeners.handlers.connection;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.listeners.BaseListener;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.player.UserManager;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

public class JoinHandler extends BaseListener {

    @EventHandler
    public void handleJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        UserManager userManager = (UserManager) Core.getInstance().getManagerRegistry().getManager(ManagerType.PRISON);

        e.setJoinMessage(Utils.colorize("&bWelcome ya 5wl! :), we hope you enjoy your stay here"));

        userManager.imprison(player);
    }

}
