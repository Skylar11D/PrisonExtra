package xyz.sk1.bukkit.prisonextra.entity.minion;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.EntityOwnable;
import net.minecraft.server.v1_8_R3.EntitySkeleton;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import xyz.sk1.bukkit.prisonextra.entity.minion.type.MinionType;
import xyz.sk1.bukkit.prisonextra.prisoner.Prisoner;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public abstract class Minion extends EntitySkeleton implements EntityOwnable {

    @Getter
    @Setter
    private MinionState state;
    @Getter(AccessLevel.PRIVATE)
    private EntityLiving owner;

    public Minion(Location location, Prisoner prisoner) {
        super(((CraftWorld)location.getWorld()).getHandle());
        this.owner = ((CraftPlayer)prisoner.getHandle()).getHandle();

        init(location);

    }

    public abstract void command();

    public abstract void follow();

    protected abstract void init(Location location);

    public abstract MinionType getType();

    @Override
    public String getOwnerUUID() {
        return this.owner.getUniqueID().toString();
    }

    @Override
    public EntityLiving getOwner() {
        return this.owner;
    }
}
