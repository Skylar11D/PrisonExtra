package xyz.sk1.bukkit.prisonextra.utils.minion;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.entity.Skeleton;
import xyz.sk1.bukkit.prisonextra.minion.Minion;
import xyz.sk1.bukkit.prisonextra.minion.type.MinionType;
import xyz.sk1.bukkit.prisonextra.player.User;
import xyz.sk1.bukkit.prisonextra.prisoner.Prisoner;
import xyz.sk1.bukkit.prisonextra.utils.minion.states.Locked;
import xyz.sk1.bukkit.prisonextra.utils.tasks.UserTask;

public class Miner extends Minion<UserTask> {

    private PathfinderGoalTargetNearestPlayer goalTargetNearestPlayer;

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

        this.goalSelector.a(0, new PathfinderGoalMoveToBlock(this));


    }

    @Override
    public void command() {getState().perform(prisoner, this);}

    @Override
    public MinionType getType() {return MinionType.MINER;}



    public static class PathfinderGoalMoveToBlock extends PathfinderGoal {
        private final EntityZombie zombie;
        private final double speed;
        private final BlockPosition targetBlock;
        private final Navigation navigation;

        public PathfinderGoalMoveToBlock(EntityZombie zombie, BlockPosition targetBlock, double speed) {
            this.zombie = zombie;
            this.targetBlock = targetBlock;
            this.speed = speed;
            this.navigation = (Navigation) this.zombie.getNavigation();
            this.a(3); // priority
        }

        @Override
        public boolean a() {
            // Check if the target block is valid and within a reasonable distance
            return targetBlock != null && targetBlock.getDistanceSquared(zombie.locX, zombie.locY, zombie.locZ) > 1;
        }

        @Override
        public void c() {
            // Start moving to the target block
            navigation.a(targetBlock.getX(), targetBlock.getY(), targetBlock.getZ(), speed);
        }

        @Override
        public boolean b() {
            // Continue moving while the target block is not reached
            return navigation.n() && !navigation.p();
        }

        @Override
        public void d() {
            // Stop moving when reached or task is stopped
            navigation.o();
        }
    }

}
