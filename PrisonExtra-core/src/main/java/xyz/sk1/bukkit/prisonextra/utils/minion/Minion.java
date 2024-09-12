package xyz.sk1.bukkit.prisonextra.utils.minion;

import net.minecraft.server.v1_8_R3.PathfinderGoalTargetNearestPlayer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Skeleton;
import xyz.sk1.bukkit.prisonextra.miner.Miner;
import xyz.sk1.bukkit.prisonextra.player.User;
import xyz.sk1.bukkit.prisonextra.utils.tasks.UserTask;

public class Minion extends Miner<UserTask> {

    private PathfinderGoalTargetNearestPlayer goalTargetNearestPlayer;

    public Minion(Location location, User player) {
        super(location.getWorld());

        Skeleton miner = (Skeleton) this.getBukkitEntity();
        miner.setMaxHealth(999);
        this.setHealth(500);
        this.getWorld().addEntity(this);

        this.setPosition(location.getX(), location.getY(), location.getZ());
        this.setSneaking(true);


    }

}
