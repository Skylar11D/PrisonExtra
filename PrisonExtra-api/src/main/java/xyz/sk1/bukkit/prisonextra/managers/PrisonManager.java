package xyz.sk1.bukkit.prisonextra.managers;

import xyz.sk1.bukkit.prisonextra.utils.tasks.PrisonTask;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author <a href="https://www.github.com/user/skylar11d">Skylar</a>
 *
 * @param <P> The target to be imprisoned
 * @param <E> The prisoner object
 * @param <T> The prisoner's tasks in the prison
 */
public abstract class PrisonManager<P, E, T extends PrisonTask> {

    private final Map<E, T> prisoners;

    public PrisonManager(){
        prisoners = new HashMap<>();
    }

    protected Map<E,T> getPrisoners(){
        return prisoners;
    }

    protected abstract E get(P player);

    /**
     * Checks whether the specified target player is a prisoner or not
     * @param player
     * @return true/false
     */
    protected abstract boolean checkPrisoner(P player);

    protected abstract void imprison(P player);

    protected abstract void release(P player);

}
