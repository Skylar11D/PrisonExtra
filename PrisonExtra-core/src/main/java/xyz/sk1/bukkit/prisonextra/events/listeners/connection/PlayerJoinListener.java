package xyz.sk1.bukkit.prisonextra.events.listeners.connection;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.sk1.bukkit.prisonextra.events.handler.connection.UserManagementHandler;
import xyz.sk1.bukkit.prisonextra.listeners.BaseListener;
import xyz.sk1.bukkit.prisonextra.listeners.PrisonEventHandler;

public class PlayerJoinListener extends BaseListener {

    private final PrisonEventHandler<PlayerJoinEvent> userHandler;

    public PlayerJoinListener(){
        this.userHandler = new UserManagementHandler();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        userHandler.handle(event);
    }

}
