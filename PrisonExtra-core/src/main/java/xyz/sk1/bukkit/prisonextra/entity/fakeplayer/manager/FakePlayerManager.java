package xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPC;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPCObserver;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.PrisonNPC;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.factory.NPCFactory;
import xyz.sk1.bukkit.prisonextra.internal.cache.Cache;
import xyz.sk1.bukkit.prisonextra.internal.configuration.YamlConfigurationHandler;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.player.UserManager;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

import java.util.Arrays;

public class FakePlayerManager implements NPCManager<PrisonNPC> {

    @Getter
    private final Cache<Integer, PrisonNPC> CACHE;
    @Getter
    private final NPCFactory npcFactory;

    @SuppressWarnings("unchecked")
    public FakePlayerManager(){
        this.CACHE = Core.getInstance().getLruCacheRegistry().getCache("fakeplayers");
        this.npcFactory = new NPCFactory();
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
        Arrays.stream(observers).forEach(observer -> observer.displayNPC(fakePlayer));
    }

    @Override
    public void load() throws Exception {
        try {
            FileConfiguration yamlConfigurationHandler = ((YamlConfigurationHandler)Core.getInstance().getSettings()).get();
            UserManager userManager = ((UserManager)Core.getInstance().getManagerRegistry().getManager(ManagerType.PRISON));

            for(String name : yamlConfigurationHandler.getConfigurationSection("entities.npcs").getKeys(false)){
                double xPoint = yamlConfigurationHandler.getDouble("entities.npcs."+name+".coordinates.x");
                double yPoint = yamlConfigurationHandler.getDouble("entities.npcs."+name+".coordinates.y");
                double zPoint = yamlConfigurationHandler.getDouble("entities.npcs."+name+".coordinates.z");
                String worldName = yamlConfigurationHandler.getString("entities.npcs."+name+".coordinates.world");

                String texture = yamlConfigurationHandler.getString("entities.npcs."+name+".appearance.texture");
                String signature = yamlConfigurationHandler.getString("entities.npcs."+name+".appearance.signature");

                Location position = new Location(Bukkit.getWorld(worldName), xPoint, yPoint, zPoint);

                NPC npc = npcFactory.createTextured(name, texture, signature, position);

                CACHE.put(npc.getId(), (PrisonNPC) npc);

            }
        } catch (Exception e){
            Utils.LOG.severe("Failed to load npcs from the yaml to cache");
            e.printStackTrace();
        }

    }

    @Override
    public ManagerType getType() {
        return ManagerType.NPC;
    }
}
