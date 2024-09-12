package xyz.sk1.bukkit.prisonextra.manager;

public interface Manager {

    default void enable(){



        load();
    }

    void load();

    ManagerType getType(ManagerType type);

}
