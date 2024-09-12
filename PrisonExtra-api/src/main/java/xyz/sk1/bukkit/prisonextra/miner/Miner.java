package xyz.sk1.bukkit.prisonextra.miner;

import net.minecraft.server.v1_8_R3.EntitySkeleton;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import xyz.sk1.bukkit.prisonextra.utilities.tasks.PrisonTask;

public abstract class Miner<T extends PrisonTask> extends EntitySkeleton {

    public Miner(World world) {
        super(((CraftWorld)world).getHandle());

    }



}
