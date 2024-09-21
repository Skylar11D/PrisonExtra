package xyz.sk1.bukkit.prisonextra.manager;

/**
 * @param <T> the exception that the loading process for each manager
 *           may throw
 */
public interface Manager<T extends Exception> {

    default void start() throws T {

        load();

    }

    void load();

    ManagerType getType();

}
