package xyz.sk1.bukkit.prisonextra.events.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import xyz.sk1.bukkit.prisonextra.events.handler.block.KeyFindingHandler;
import xyz.sk1.bukkit.prisonextra.listeners.BaseListener;
import xyz.sk1.bukkit.prisonextra.listeners.PrisonEventHandler;

public class BlockBreakListener extends BaseListener {

    private final PrisonEventHandler<BlockBreakEvent> keyfindingHandler;

    public BlockBreakListener() {
        this.keyfindingHandler = new KeyFindingHandler();
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){

    }

}
