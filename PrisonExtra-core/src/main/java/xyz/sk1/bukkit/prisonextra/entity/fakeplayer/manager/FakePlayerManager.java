package xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
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
    //private final Cache<Integer, PrisonNPC> cache;
    @Getter
    private final Map<Integer, NPC> cache;
    @Getter
    private final List<NpcObserver> npcObservers;
    @Getter
    private final NPCFactory npcFactory;

    @SuppressWarnings("unchecked")
    public FakePlayerManager(){
        this.cache = new HashMap<>();
        this.npcFactory = new NPCFactory();
        this.npcObservers = new ArrayList<>();
    }

    @Override
    public void register(NPC fakePlayer) {
        cache.put(fakePlayer.getId(), fakePlayer);
    }

    @Override
    public void unregister(NPC fakePlayer) {
        cache.remove(fakePlayer.getId());
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

    @Override
    public void showNpcTo(Player player, NPC npc) {

        //Destory the npc entity if t's already shown
        if(npc.getId() != 0){
            ((CraftPlayer)player).getHandle().playerConnection.sendPacket(npc.getDestroy());
        }
        //Sends the required information about the entity to the client
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(npc.getInfoPacket());
        //Sends the appearance and game profile properties for the entity
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(npc.getMetadata());
        //Spawn the entity
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(npc.getSpawnPacket());

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
