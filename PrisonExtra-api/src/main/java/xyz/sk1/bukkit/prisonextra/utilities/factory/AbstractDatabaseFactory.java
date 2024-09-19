package xyz.sk1.bukkit.prisonextra.utilities.factory;

import xyz.sk1.bukkit.prisonextra.internal.storage.PDatabase;
import xyz.sk1.bukkit.prisonextra.internal.storage.types.DatabaseType;

public abstract class AbstractDatabaseFactory {

    public abstract PDatabase createDatabase(DatabaseType type);

}
