package xyz.sk1.bukkit.prisonextra.utils.factory;

import xyz.sk1.bukkit.prisonextra.internal.storage.Database;
import xyz.sk1.bukkit.prisonextra.internal.storage.types.DatabaseType;

public abstract class AbstractDatabaseFactory {

    public abstract Database createDatabase(DatabaseType type);

}
