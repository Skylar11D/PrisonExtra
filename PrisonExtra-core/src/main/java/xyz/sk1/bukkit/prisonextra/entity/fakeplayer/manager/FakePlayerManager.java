package xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager;

import lombok.Getter;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPCObserver;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.PrisonNPC;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.factory.NPCFactory;
import xyz.sk1.bukkit.prisonextra.internal.cache.Cache;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;

public class FakePlayerManager implements NPCManager<PrisonNPC> {


    private final Cache<Integer, PrisonNPC> CACHE;
    @Getter
    private final NPCFactory factory;

    @SuppressWarnings("unchecked")
    public FakePlayerManager(){
        this.CACHE = Core.getInstance().getLruCacheRegistry().getCache("fakeplayers");
        this.factory = new NPCFactory();
    }

    @Override
    public void register(PrisonNPC fakePlayer) {
        CACHE.put(fakePlayer.getId(), fakePlayer);
    }

    @Override
    public void unregister(PrisonNPC fakePlayer) {
        CACHE.remove(fakePlayer.getId());
    }

    @Override
    public void syncNPC(PrisonNPC fakePlayer, NPCObserver... observers) {
        this.notifyPrisoners(fakePlayer, observers);
    }

    private void notifyPrisoners(PrisonNPC fakePlayer, NPCObserver... observers){
        for(NPCObserver npcObserver : observers){
            npcObserver.displayNPC(fakePlayer);
        }
    }

    @Override
    public void load() throws Exception {

    }

    @Override
    public ManagerType getType() {
        return ManagerType.NPC;
    }
}
