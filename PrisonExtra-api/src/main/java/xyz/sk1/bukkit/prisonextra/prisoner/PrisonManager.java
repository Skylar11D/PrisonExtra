package xyz.sk1.bukkit.prisonextra.prisoner;

import xyz.sk1.bukkit.prisonextra.manager.Manager;
import xyz.sk1.bukkit.prisonextra.utilities.tasks.PrisonTask;

import java.util.List;
import java.util.Map;

/**
 * @Author <a href="https://www.github.com/user/skylar11d">Skylar</a>
 *
 * @param <P> The target to be imprisoned
 * @param <E> The prisoner object
 * @param <T> The prisoner's tasks in the prison
 */
public interface PrisonManager<P, E, T extends PrisonTask> extends Manager {

    Map<E, List<T>> getPrisoners();

    E get(P player);

    /**
     * Checks whether the specified target player is a prisoner or not
     * @param player
     * @return true/false
     */
    boolean checkPrisoner(P player);

    void imprison(P player);

    void release(P player);

}
