package xyz.sk1.bukkit.prisonextra.miner;

import net.minecraft.server.v1_8_R3.EntitySkeleton;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftSkeleton;
import xyz.sk1.bukkit.prisonextra.utilities.tasks.PrisonTask;

public abstract class Miner<T extends PrisonTask> extends CraftSkeleton {

    public Miner(CraftServer server, EntitySkeleton entity) {
        super(server, entity);
    }

}
