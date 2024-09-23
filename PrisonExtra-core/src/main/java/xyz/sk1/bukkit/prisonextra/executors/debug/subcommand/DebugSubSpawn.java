package xyz.sk1.bukkit.prisonextra.executors.debug.subcommand;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import xyz.sk1.bukkit.prisonextra.entity.minion.Miner;
import xyz.sk1.bukkit.prisonextra.entity.minion.Minion;
import xyz.sk1.bukkit.prisonextra.entity.minion.factory.MinionFactory;
import xyz.sk1.bukkit.prisonextra.entity.minion.type.MinionType;
import xyz.sk1.bukkit.prisonextra.executors.Subcommand;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;
import xyz.sk1.bukkit.prisonextra.utils.CustomEntityRegistry;

import java.lang.reflect.Field;
import java.util.Map;

public class DebugSubSpawn implements Subcommand {

    @Override
    public void dispatch(Player sender, String[] args) {

        if(!args[1].equalsIgnoreCase("miner")) {
            return;
        }

        MinionFactory minionFactory = new MinionFactory();
        Minion minion = minionFactory.createMinion(MinionType.MINER, sender.getLocation()).get();

        try {
            // Get the 'c' and 'd' maps from the EntityTypes class (these store the entity mappings)
            Field c = EntityTypes.class.getDeclaredField("c");
            Field d = EntityTypes.class.getDeclaredField("d");
            c.setAccessible(true);
            d.setAccessible(true);

            Map<String, Class<? extends Entity>> cMap = (Map<String, Class<? extends Entity>>) c.get(null);
            Map<Class<? extends Entity>, String> dMap = (Map<Class<? extends Entity>, String>) d.get(null);

            // Unregister the default zombie class
            cMap.remove("Skeleton");
            dMap.remove(EntitySkeleton.class);

            // Register the custom entity with the same name
            cMap.put("Skeleton", EntitySkeleton.class);
            dMap.put(Miner.class, "Skeleton");
        } catch (Exception e) {
            e.printStackTrace();
        }


        sender.sendMessage(Utils.colorize("&6entity spawned"));
        WorldServer worldServer = ((CraftWorld)sender.getWorld()).getHandle();
        worldServer.addEntity(minion);

        Utils.LOG.info("entity type: " + EntityTypes.b("Skeleton"));

    }
}
