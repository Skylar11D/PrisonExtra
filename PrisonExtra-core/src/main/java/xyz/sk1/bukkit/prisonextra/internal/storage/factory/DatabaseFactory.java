package xyz.sk1.bukkit.prisonextra.internal.storage.factory;

import xyz.sk1.bukkit.prisonextra.internal.configuration.ConfigurationHandler;
import xyz.sk1.bukkit.prisonextra.internal.configuration.YamlConfigurationHandler;
import xyz.sk1.bukkit.prisonextra.internal.storage.DatabaseConnector;
import xyz.sk1.bukkit.prisonextra.internal.storage.sql.MySQLDatabase;
import xyz.sk1.bukkit.prisonextra.internal.storage.mongo.MongoDatabase;
import xyz.sk1.bukkit.prisonextra.internal.storage.types.DatabaseType;
import xyz.sk1.bukkit.prisonextra.utilities.factory.AbstractDatabaseFactory;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public class DatabaseFactory extends AbstractDatabaseFactory {

    @Override
    public DatabaseConnector createDatabase(DatabaseType type, ConfigurationHandler configurationHandler) {

        switch (type) {
            case MYSQL: {
                return new MySQLDatabase(configurationHandler);
            }

            case MONGODB: {
                return new MongoDatabase();
            }
        }

        return null;
    }
}
