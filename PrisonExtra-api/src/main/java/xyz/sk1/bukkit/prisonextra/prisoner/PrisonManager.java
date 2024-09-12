package xyz.sk1.bukkit.prisonextra.prisoner;

import xyz.sk1.bukkit.prisonextra.manager.Manager;
import xyz.sk1.bukkit.prisonextra.utilities.tasks.PrisonTask;

import java.util.Map;

/**
 * @Author <a href="https://www.github.com/user/skylar11d">Skylar</a>
 *
 * @param <P> The target to be imprisoned
 * @param <E> The prisoner object
 * @param <T> The prisoner's tasks in the prison
 */
public interface PrisonManager<P, E, T extends PrisonTask> extends Manager {

    Map<E,T> getPrisoners();

    public E get(P player);

    /**
     * Checks whether the specified target player is a prisoner or not
     * @param player
     * @return true/false
     */
    public boolean checkPrisoner(P player);

    public void imprison(P player);

    public void release(P player);

}
