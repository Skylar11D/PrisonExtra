package xyz.sk1.bukkit.prisonextra.events.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.FakePlayerManager;
import xyz.sk1.bukkit.prisonextra.events.handler.interact.NpcInteractHandler;
import xyz.sk1.bukkit.prisonextra.listeners.BaseListener;
import xyz.sk1.bukkit.prisonextra.listeners.PrisonEventHandler;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

public class PlayerInteractListener extends BaseListener {

    private final PrisonEventHandler<PlayerInteractAtEntityEvent> npcHandler;

    public PlayerInteractListener(){
        this.npcHandler = new NpcInteractHandler();
    }

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event){
        int id = event.getRightClicked().getEntityId();
        FakePlayerManager npcManager = (FakePlayerManager) Core.getInstance().getManagerRegistry().getManager(ManagerType.NPC);

        if(npcManager.getCache().containsKey(id)){
            Utils.LOG.info("Condition succeeded");
            npcHandler.handle(event);
        }
        Utils.LOG.info("Condition ignored");

    }

}
