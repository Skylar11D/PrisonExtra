package xyz.sk1.bukkit.prisonextra.entity.minion;

import net.minecraft.server.v1_8_R3.PathfinderGoalRandomLookaround;
import org.bukkit.Location;
import org.bukkit.entity.Skeleton;
import xyz.sk1.bukkit.prisonextra.entity.minion.type.MinionType;
import xyz.sk1.bukkit.prisonextra.prisoner.Prisoner;
import xyz.sk1.bukkit.prisonextra.entity.minion.states.Locked;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public class Miner extends Minion {

    // This is the actual personal miner for each player (experimental)

    public Miner(Location location, Prisoner prisoner) {
        super(location, prisoner);

    }

    @Override
    protected void init(Location location){


        super.setState(new Locked()); //default state

        Skeleton miner = (Skeleton) this.getBukkitEntity();
        miner.setMaxHealth(999);
        this.setHealth(500);
        this.getWorld().addEntity(this);

        this.setCustomName("Miner");
        this.setCustomNameVisible(true);
        this.setInvisible(false);

        this.setPosition(location.getX(), location.getY(), location.getZ());
        this.persistent = true;

        //experimental
        //this.setDefaultMinerPathfinders();

    }

    @Override
    public void command() {
        getState().perform((Prisoner) getOwner(), this);
    }

    @Override
    public void follow() {

    }

    private void setDefaultMinerPathfinders(){
        //this.goalSelector.a(0, new PathfinderGoalMoveToStone(this, 1.0));
        //this.goalSelector.a(1, new PathfinderGoalFollowOwner(this, 2, 2, 5));
        //this.goalSelector.a(2, new PathfinderGoalRandomLookaround(this));
    }

    @Override
    public MinionType getType() {return MinionType.MINER;}

}
