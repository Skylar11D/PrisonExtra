package xyz.sk1.bukkit.prisonextra.internal.storage;

import java.io.Closeable;

/**
 * Generic interface responsible for database connectivity
 * @param <C> the type of connection
 */
public interface DatabaseConnector<C> extends Closeable {

    void connect();

    C getConnection();

}