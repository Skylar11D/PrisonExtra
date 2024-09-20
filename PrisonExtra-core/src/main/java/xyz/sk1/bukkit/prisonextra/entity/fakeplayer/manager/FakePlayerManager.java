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
import xyz.sk1.bukkit.prisonextra.internal.configuration.YamlConfigurationHandler;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;
import xyz.sk1.bukkit.prisonextra.utils.tasks.NPCSync;

import java.util.*;

public class FakePlayerManager implements NPCManager<PrisonNPC> {

    //@Getter
    //private final Cache<Integer, PrisonNPC> CACHE;
    @Getter
    private final Map<Integer, PrisonNPC> CACHE;
    @Getter
    private final List<NPCObserver> npcObservers;
    @Getter
    private final NPCFactory npcFactory;

    @SuppressWarnings("unchecked")
    public FakePlayerManager(){
        this.CACHE = new HashMap<>();
        this.npcFactory = new NPCFactory();
        this.npcObservers = new ArrayList<>();
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
    public void syncNPC(PrisonNPC fakePlayer, List<NPCObserver> observers) {
        this.notifyPrisoners(fakePlayer, observers);
    }

    @Override
    public void registerObserver(NPCObserver observer) {
        npcObservers.add(observer);
    }

    @Override
    public void removeObserver(NPCObserver observer) {
        npcObservers.remove(observer);
    }

    private void notifyPrisoners(PrisonNPC fakePlayer, List<NPCObserver> observers){
        //Arrays.stream(observers).forEach(observer -> observer.displayNPC(fakePlayer));
        observers.stream().forEach(npcObserver -> npcObserver.displayNPC(fakePlayer));
    }

    @Override
    public void load() throws Exception {
        try {
            FileConfiguration yamlConfigurationHandler = ((YamlConfigurationHandler)Core.getInstance().getSettings()).get();

            for(String name : yamlConfigurationHandler.getConfigurationSection("entities.npcs.list").getKeys(false)){
                double xPoint = yamlConfigurationHandler.getDouble("entities.npcs.list."+name+".coordinates.x");
                double yPoint = yamlConfigurationHandler.getDouble("entities.npcs.list."+name+".coordinates.y");
                double zPoint = yamlConfigurationHandler.getDouble("entities.npcs.list."+name+".coordinates.z");
                String worldName = yamlConfigurationHandler.getString("entities.npcs.list."+name+".coordinates.world");

                String texture = yamlConfigurationHandler.getString("entities.npcs.global.appearance.texture");
                String signature = yamlConfigurationHandler.getString("entities.npcs.global.appearance.signature");

                Location position = new Location(Bukkit.getWorld(worldName), xPoint, yPoint, zPoint);

                NPC npc = npcFactory.createTextured(name, texture, signature, position);

                CACHE.put(npc.getId(), (PrisonNPC) npc);

            }
        } catch (Exception e){
            Utils.LOG.severe("Failed to load npcs from the yaml to cache");
            e.printStackTrace();
        }

        new NPCSync().runTaskTimerAsynchronously(Core.getInstance(), 20*5, 20*14);

    }

    @Override
    public ManagerType getType() {
        return ManagerType.NPC;
    }
}
