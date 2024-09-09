package xyz.sk1.bukkit.prisonextra.managers;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.sk1.bukkit.prisonextra.player.Prisoner;
import xyz.sk1.bukkit.prisonextra.utils.tasks.PrisonTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PrisonManager<Entity extends Prisoner, Task extends PrisonTask> {

    private final Map<Entity, Task> prisoners;

    public PrisonManager(){
        prisoners = new HashMap<>();
    }

    abstract List<Entity> getPrisoners();

    abstract boolean checkPrisoner(Entity player);

    abstract void imprison(Entity player);

    abstract void release(Entity player);

}
