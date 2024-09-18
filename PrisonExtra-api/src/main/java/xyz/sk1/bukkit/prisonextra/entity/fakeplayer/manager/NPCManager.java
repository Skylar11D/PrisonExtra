package xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager;

import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPC;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPCObserver;
import xyz.sk1.bukkit.prisonextra.manager.Manager;

public interface NPCManager<N extends NPC> extends Manager {

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
     * Acknowledge all players in this case prisoners about
     * the fakePlayer entity to make it visible and interactable
     * @param fakePlayer
     */
    void syncNPC(N fakePlayer, NPCObserver... npcObservers);

}
