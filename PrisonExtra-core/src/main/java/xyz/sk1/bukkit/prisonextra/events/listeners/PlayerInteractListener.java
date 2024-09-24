package xyz.sk1.bukkit.prisonextra.events.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import xyz.sk1.bukkit.prisonextra.events.handler.block.WandSelectionHandler;
import xyz.sk1.bukkit.prisonextra.listeners.BaseListener;
import xyz.sk1.bukkit.prisonextra.listeners.PrisonEventHandler;

public class PlayerInteractListener extends BaseListener {

    private final PrisonEventHandler<PlayerInteractEvent> wandselection;


    public PlayerInteractListener() {
        this.wandselection = new WandSelectionHandler();
    }

    @EventHandler
    public void onContact(PlayerInteractEvent event){

        if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName() == "&c&lWand")
            wandselection.handle(event);

    }

}
