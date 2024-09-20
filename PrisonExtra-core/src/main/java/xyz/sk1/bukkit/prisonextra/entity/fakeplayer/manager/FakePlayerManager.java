package xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPC;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NpcObserver;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.factory.NPCFactory;
import xyz.sk1.bukkit.prisonextra.internal.configuration.YamlConfigurationHandler;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;
import xyz.sk1.bukkit.prisonextra.utils.tasks.NPCSync;

import java.util.*;

public class FakePlayerManager implements NpcManager<NPC> {

    //@Getter
    //private final Cache<Integer, PrisonNPC> CACHE;
    @Getter
    private final Map<Integer, NPC> CACHE;
    @Getter
    private final List<NpcObserver> npcObservers;
    @Getter
    private final NPCFactory npcFactory;

    @SuppressWarnings("unchecked")
    public FakePlayerManager(){
        this.CACHE = new HashMap<>();
        this.npcFactory = new NPCFactory();
        this.npcObservers = new ArrayList<>();
    }

    @Override
    public void register(NPC fakePlayer) {
        CACHE.put(fakePlayer.getId(), fakePlayer);
    }

    @Override
    public void unregister(NPC fakePlayer) {
        CACHE.remove(fakePlayer.getId());
    }

    @Override
    public void syncNPC(NPC fakePlayer, List<NpcObserver> observers) {
        this.notifyPrisoners(fakePlayer, observers);
    }

    @Override
    public void registerObserver(NpcObserver observer) {
        npcObservers.add(observer);
    }

    @Override
    public void removeObserver(NpcObserver observer) {
        npcObservers.remove(observer);
    }

    private void notifyPrisoners(NPC fakePlayer, List<NpcObserver> observers){
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

                Utils.LOG.info("texture: " + texture);
                Utils.LOG.info("signature: " + signature);

                Location position = new Location(Bukkit.getWorld(worldName), xPoint, yPoint, zPoint);

                NPC npc = npcFactory.createTextured(name, texture, signature, position);

                register(npc);

            }
        } catch (Exception e){
            Utils.LOG.severe("Failed to load npcs from the yaml to cache");
            e.printStackTrace();
        }

        //runs the cycl to synchronize the available npcs
        new NPCSync().runTaskTimer(Core.getInstance(), 20*5, 20*14);

    }

    @Override
    public ManagerType getType() {
        return ManagerType.NPC;
    }
}
