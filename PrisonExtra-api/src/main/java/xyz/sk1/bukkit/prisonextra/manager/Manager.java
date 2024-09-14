package xyz.sk1.bukkit.prisonextra.manager;

public interface Manager {

    default void start() throws Exception {

        load();

    }

    void load() throws Exception;

    ManagerType getType();

}
