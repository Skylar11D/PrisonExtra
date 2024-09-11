package xyz.sk1.bukkit.prisonextra.utilities.factory;

import xyz.sk1.bukkit.prisonextra.internal.storage.Database;
import xyz.sk1.bukkit.prisonextra.internal.storage.MySQLDatabase;
import xyz.sk1.bukkit.prisonextra.internal.storage.mongo.MongoDatabase;
import xyz.sk1.bukkit.prisonextra.internal.storage.types.DatabaseType;
import xyz.sk1.bukkit.prisonextra.utils.factory.AbstractDatabaseFactory;

public class DataBaseFactory extends AbstractDatabaseFactory {

    @Override
    public Database createDatabase(DatabaseType type) {

        switch (type) {
            case MYSQL: {
                return new MongoDatabase();
            }

            case MONGODB: {
                return new MySQLDatabase();
            }
        }

        return null;
    }
}
