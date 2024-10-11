package xyz.sk1.bukkit.prisonextra.internal.storage.mongo;

import org.apache.logging.log4j.core.appender.db.nosql.mongo.MongoDBConnection;
import xyz.sk1.bukkit.prisonextra.internal.storage.DatabaseConnector;

import java.io.IOException;

public class MongoConnector implements DatabaseConnector<MongoDBConnection> {

    @Override
    public void connect() {

    }

    @Override
    public MongoDBConnection getConnection() {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
