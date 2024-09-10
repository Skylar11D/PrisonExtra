package xyz.sk1.bukkit.prisonextra.listeners.handlers.connection;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.sk1.bukkit.prisonextra.listeners.BaseListener;

public class JoinHandler extends BaseListener {

    @EventHandler
    public void handleJoin(PlayerJoinEvent e){
        e.setJoinMessage("Welcome ya 5wl! :), we hope you enjoy your stay here");
    }

}
