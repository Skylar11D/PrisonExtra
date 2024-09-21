package xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPC;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NpcObserver;
import xyz.sk1.bukkit.prisonextra.manager.Manager;

import java.util.List;

public interface NpcManager<N extends NPC> extends Manager<Exception> {

    /**
     * register the NPC entity into the local cache
     * @param fakePlayer
     */
    void register(N fakePlayer);

    /**
     * removes the NPC entity from the local cache
     * @param fakePlayer
     */
    void unregister(N fakePlayer);

    /**
     * Register the player who will be able to observe all fake players in the server
     * @param observer
     */
    void registerObserver(NpcObserver observer);

    /**
     * Removes the observers
     * @param observer
     */
    void removeObserver(NpcObserver observer);

    /**
     * Special method to display a loaded npc to the player
     * @param player
     * @param npc
     */
    void showNpcTo(Player player, N npc);

}
