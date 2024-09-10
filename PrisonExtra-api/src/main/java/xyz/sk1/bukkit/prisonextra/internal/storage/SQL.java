package xyz.sk1.bukkit.prisonextra.internal.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Closeable;
import java.sql.Connection;

@Getter
@AllArgsConstructor
public abstract class SQL implements Closeable {

    private String host;
    private String database;
    private int port;
    private String username;
    private String password;
    private String table;

    protected abstract void connect();
    protected abstract Connection getConnection();

}
