package xyz.sk1.bukkit.prisonextra.utilities.factory;

import xyz.sk1.bukkit.prisonextra.internal.configuration.ConfigurationHandler;
import xyz.sk1.bukkit.prisonextra.internal.storage.DatabaseConnector;
import xyz.sk1.bukkit.prisonextra.internal.storage.types.DatabaseType;

public abstract class AbstractDatabaseFactory {

    public abstract DatabaseConnector<?> createDatabase(DatabaseType type, ConfigurationHandler configurationHandler);

}
