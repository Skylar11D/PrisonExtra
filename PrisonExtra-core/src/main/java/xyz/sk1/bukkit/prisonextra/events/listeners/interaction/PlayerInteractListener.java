package xyz.sk1.bukkit.prisonextra.events.listeners.interaction;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.FakePlayerManager;
import xyz.sk1.bukkit.prisonextra.events.handler.interact.NpcInteractHandler;
import xyz.sk1.bukkit.prisonextra.listeners.BaseListener;
import xyz.sk1.bukkit.prisonextra.listeners.PrisonEventHandler;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;

public class PlayerInteractListener extends BaseListener {

    private final PrisonEventHandler<PlayerInteractEntityEvent> npcHandler;

    public PlayerInteractListener(){
        this.npcHandler = new NpcInteractHandler();
    }

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event){
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        FakePlayerManager npcManager = (FakePlayerManager) Core.getInstance().getManagerRegistry().getManager(ManagerType.NPC);

        if(npcManager.getCache().containsKey(entity.getEntityId())){
            npcHandler.handle(event);
        }

    }

}
