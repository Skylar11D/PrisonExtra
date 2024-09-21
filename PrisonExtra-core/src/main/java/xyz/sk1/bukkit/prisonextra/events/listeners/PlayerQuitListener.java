package xyz.sk1.bukkit.prisonextra.events.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.sk1.bukkit.prisonextra.events.handler.connection.QuitHandler;
import xyz.sk1.bukkit.prisonextra.listeners.BaseListener;
import xyz.sk1.bukkit.prisonextra.listeners.PrisonEventHandler;

public class PlayerQuitListener extends BaseListener {

    private PrisonEventHandler<PlayerQuitEvent> eventHandler;

    public PlayerQuitListener(){
        this.eventHandler = new QuitHandler();
    }

    @EventHandler
    public void onLeaving(PlayerQuitEvent event){
        eventHandler.handle(event);
    }

}
