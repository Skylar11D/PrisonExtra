package xyz.sk1.bukkit.prisonextra.entity.minion.pathfinder;

import net.minecraft.server.v1_8_R3.*;
import xyz.sk1.bukkit.prisonextra.entity.minion.Minion;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public class PathfinderGoalMoveToStone extends PathfinderGoal {

    //experimental

    private final Minion zombie;;
    private BlockPosition targetBlock;
    private final double speed;
    private final Navigation navigation;
    private EntityInsentient a;
    private EntityLiving b;
    private double f;
    private float g;

    public PathfinderGoalMoveToStone(Minion zombie, double speed) {
        this.zombie = zombie;
        this.speed = speed;
        this.navigation = (Navigation) this.zombie.getNavigation();
        this.a(3); // Set priority
    }

    @Override
    public boolean a() {
        if (targetBlock == null) {
            findNearestStoneBlock();
        }
        return targetBlock != null && getDistanceSquared(targetBlock) > 1;
    }

    @Override
    public void c() {
        if (targetBlock != null) {
            //Navigation#walkTo(x, y, z);
            navigation.a(targetBlock.getX(), targetBlock.getY(), targetBlock.getZ(), speed);
        }
    }

    @Override
    public boolean b() {
        return targetBlock != null && navigation.m() && !hasReachedTarget();
    }

    @Override
    public void d() {
        stopNavigation();
    }

    private void findNearestStoneBlock() {
        BlockPosition pos = new BlockPosition(this.zombie.locX, this.zombie.locY, this.zombie.locZ);
        for (int x = -20; x <= 20; x++) {
            for (int y = -20; y <= 20; y++) {
                for (int z = -20; z <= 20; z++) {
                    BlockPosition checkPos = pos.a(x, y, z);
                    Block block = this.zombie.world.getType(checkPos).getBlock();
                    if (block == Blocks.STONE) {
                        this.targetBlock = checkPos;
                        return;
                    }
                }
            }
        }
    }

    private boolean hasReachedTarget() {
        // Calculate the distance squared to the target block
        double dx = targetBlock.getX() - this.zombie.locX;
        double dy = targetBlock.getY() - this.zombie.locY;
        double dz = targetBlock.getZ() - this.zombie.locZ;
        double distanceSquared = dx * dx + dy * dy + dz * dz;
        // Consider reached if within 2 blocks (adjust if necessary)
        return distanceSquared <= 2 * 2;
    }

    private double getDistanceSquared(BlockPosition target) {
        double dx = target.getX() - this.zombie.locX;
        double dy = target.getY() - this.zombie.locY;
        double dz = target.getZ() - this.zombie.locZ;
        return dx * dx + dy * dy + dz * dz;
    }

    private void stopNavigation() {
        // Since o() is private, handle stopping the navigation by setting the path to null or similar
        if (navigation instanceof Navigation) {
            navigation.n(); // Stop the navigation manually
        }
    }
}
