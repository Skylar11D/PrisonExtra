package xyz.sk1.bukkit.prisonextra.internal.storage;

import java.io.Closeable;
import java.sql.Connection;

public abstract class Database implements Closeable {

    public abstract void connect();
    public abstract Connection getConnection();

}