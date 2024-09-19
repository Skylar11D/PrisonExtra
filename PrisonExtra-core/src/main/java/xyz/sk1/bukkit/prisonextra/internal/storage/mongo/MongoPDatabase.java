package xyz.sk1.bukkit.prisonextra.internal.storage.mongo;

import xyz.sk1.bukkit.prisonextra.internal.storage.PDatabase;

import java.io.IOException;
import java.sql.Connection;

public class MongoPDatabase extends PDatabase {

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
