package xyz.sk1.bukkit.prisonextra.manager;

import java.sql.SQLException;

public interface Manager {

    default void start() throws SQLException {

        load();
    }

    void load() throws SQLException;

    ManagerType getType();

}
