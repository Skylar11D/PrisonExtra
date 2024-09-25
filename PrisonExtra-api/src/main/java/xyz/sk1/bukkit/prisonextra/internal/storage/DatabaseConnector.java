package xyz.sk1.bukkit.prisonextra.internal.storage;

import com.google.gson.JsonObject;
import xyz.sk1.bukkit.prisonextra.internal.configuration.ConfigurationHandler;

import java.io.Closeable;
import java.sql.Connection;

public abstract class DatabaseConnector implements Closeable {

    private String hostname;
    private String database;
    private int port;
    private String username;
    private String passphrase;

    public abstract void connect();

    public abstract Connection getConnection();

}