package xyz.sk1.bukkit.prisonextra.internal.storage;

import java.io.Closeable;
import java.sql.Connection;

public interface DatabaseConnector extends Closeable {

    void connect();

    Connection getConnection();

}