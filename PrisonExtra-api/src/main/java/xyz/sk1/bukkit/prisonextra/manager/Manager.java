package xyz.sk1.bukkit.prisonextra.manager;

public interface Manager {

    default void start() {

        load();

    }

    void load();

    ManagerType getType();

    default void finish(){

    }

}
