package xyz.sk1.bukkit.prisonextra.minion;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.v1_8_R3.EntitySkeleton;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import xyz.sk1.bukkit.prisonextra.minion.type.MinionType;
import xyz.sk1.bukkit.prisonextra.utilities.tasks.PrisonTask;

public abstract class Minion extends EntitySkeleton {

    @Getter
    @Setter
    private MinionState state;

    public Minion(World world) {
        super(((CraftWorld)world).getHandle());

    }

    public abstract void command();

    public abstract MinionType getType();

}
