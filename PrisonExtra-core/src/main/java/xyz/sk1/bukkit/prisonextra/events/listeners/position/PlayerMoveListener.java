package xyz.sk1.bukkit.prisonextra.events.listeners.position;

import org.bukkit.event.player.PlayerMoveEvent;
import xyz.sk1.bukkit.prisonextra.events.handler.position.StareHandler;
import xyz.sk1.bukkit.prisonextra.listeners.BaseListener;
import xyz.sk1.bukkit.prisonextra.listeners.PrisonEventHandler;

public class PlayerMoveListener extends BaseListener {

    private final PrisonEventHandler<PlayerMoveEvent> stareHandler;

    public PlayerMoveListener(){
        this.stareHandler = new StareHandler();
    }

}
