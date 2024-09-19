package xyz.sk1.bukkit.prisonextra.internal.storage.factory;

import xyz.sk1.bukkit.prisonextra.internal.storage.PDatabase;
import xyz.sk1.bukkit.prisonextra.internal.storage.sql.MySQLPDatabase;
import xyz.sk1.bukkit.prisonextra.internal.storage.mongo.MongoPDatabase;
import xyz.sk1.bukkit.prisonextra.internal.storage.types.DatabaseType;
import xyz.sk1.bukkit.prisonextra.utilities.factory.AbstractDatabaseFactory;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public class DatabaseFactory extends AbstractDatabaseFactory {

    @Override
    public PDatabase createDatabase(DatabaseType type) {

        switch (type) {
            case MYSQL: {
                return new MongoPDatabase();
            }

            case MONGODB: {
                return new MySQLPDatabase();
            }
        }

        return null;
    }
}
