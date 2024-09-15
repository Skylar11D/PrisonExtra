package xyz.sk1.bukkit.prisonextra.utils.minion;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.entity.Skeleton;
import xyz.sk1.bukkit.prisonextra.minion.Minion;
import xyz.sk1.bukkit.prisonextra.minion.type.MinionType;
import xyz.sk1.bukkit.prisonextra.player.User;
import xyz.sk1.bukkit.prisonextra.prisoner.Prisoner;
import xyz.sk1.bukkit.prisonextra.utils.minion.pathfinder.PathfinderGoalMoveToStone;
import xyz.sk1.bukkit.prisonextra.utils.minion.states.Locked;

public class Miner extends Minion { ;

    private final Prisoner prisoner;

    public Miner(Location location, User player) {
        super(location.getWorld());

        this.prisoner = player;

        setState(new Locked()); //default state

        Skeleton miner = (Skeleton) this.getBukkitEntity();
        miner.setMaxHealth(999);
        this.setHealth(500);
        this.getWorld().addEntity(this);

        this.setPosition(location.getX(), location.getY(), location.getZ());
        this.setSneaking(true);
        this.persistent = true;

        this.goalSelector.a(0, new PathfinderGoalMoveToStone(this, 1.0));


    }

    @Override
    public void command() {
        getState().perform(prisoner, this);
    }

    @Override
    public MinionType getType() {return MinionType.MINER;}

}
