package xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager;

import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPC;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NpcObserver;
import xyz.sk1.bukkit.prisonextra.manager.Manager;

import java.util.List;

public interface NpcManager<N extends NPC> extends Manager {

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
     * Acknowledge all npc observers about
     * the fakePlayer entity to make it visible and interactable
     * @param fakePlayer the npc to have its packets synchronized with the server
     * @param npcObservers users who will be acknowledged about <code>fakePlayer</code>'s existence
     */
    void syncNPC(N fakePlayer, List<NpcObserver> npcObservers);

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

}
