package xyz.sk1.bukkit.prisonextra.entity.minion;

import org.bukkit.Location;
import org.bukkit.entity.Skeleton;
import xyz.sk1.bukkit.prisonextra.entity.minion.type.MinionType;
import xyz.sk1.bukkit.prisonextra.player.User;
import xyz.sk1.bukkit.prisonextra.prisoner.Prisoner;
import xyz.sk1.bukkit.prisonextra.entity.minion.pathfinder.PathfinderGoalMoveToStone;
import xyz.sk1.bukkit.prisonextra.entity.minion.states.Locked;

public class Miner extends Minion {

    // This is the actual personal miner for each player (experimental)

    public Miner(Location location, Prisoner prisoner) {
        super(location.getWorld(), prisoner);

        this.init(location);

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

        this.goalSelector.a(0, new PathfinderGoalMoveToStone(this, 1.0));

    }

    @Override
    public void command() {
        getState().perform((Prisoner) getOwner(), this);
    }

    @Override
    public void follow() {

    }

    @Override
    public MinionType getType() {return MinionType.MINER;}

}
