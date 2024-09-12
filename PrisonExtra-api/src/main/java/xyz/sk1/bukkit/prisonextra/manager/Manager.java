package xyz.sk1.bukkit.prisonextra.manager;

public interface Manager {

    default void launch(){



        load();
    }

    void load();

}
