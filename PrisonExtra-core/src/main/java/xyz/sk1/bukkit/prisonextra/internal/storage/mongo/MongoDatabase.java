package xyz.sk1.bukkit.prisonextra.internal.storage.mongo;

import xyz.sk1.bukkit.prisonextra.internal.storage.Database;

import java.io.IOException;
import java.sql.Connection;

public class MongoDatabase extends Database {

    @Override
    public void connect() {

    }

    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
