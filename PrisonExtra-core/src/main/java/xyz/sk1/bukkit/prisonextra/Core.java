package xyz.sk1.bukkit.prisonextra;

import xyz.sk1.bukkit.prisonextra.internal.PluginManager;
import xyz.sk1.bukkit.prisonextra.internal.storage.Database;
import xyz.sk1.bukkit.prisonextra.internal.storage.types.DatabaseType;
import xyz.sk1.bukkit.prisonextra.player.UserManager;
import xyz.sk1.bukkit.prisonextra.utilities.cosmetics.CosmeticsManager;
import xyz.sk1.bukkit.prisonextra.utilities.factory.DataBaseFactory;
import xyz.sk1.bukkit.prisonextra.utilities.factory.MySQLConnectionFactory;
import xyz.sk1.bukkit.prisonextra.utilities.housing.HouseManager;
import xyz.sk1.bukkit.prisonextra.utils.factory.AbstractDatabaseFactory;

import java.io.IOException;

public class Core extends Base {

    private static volatile Core instance;
    private PluginManager pluginManager;
    private UserManager playerManager;
    private HouseManager houseManager;
    private CosmeticsManager cosmeticsManager;
    private Database database;
    private AbstractDatabaseFactory abstractDatabaseFactory;


    public void onceEnabled() {
        instance = this;

        abstractDatabaseFactory = new DataBaseFactory();
        database = abstractDatabaseFactory.createDatabase(DatabaseType.MYSQL);
        database.connect();

        this.pluginManager = new PluginManager();
        this.playerManager = new UserManager();

    }

    @Override
    public void fini() {

        try {
            database.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static Core getInstance(){

        if(instance != null){
            return instance;
        }

        synchronized (Core.class) {
            if(instance == null){
                instance = new Core();
            }
        }

        return instance;
    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }

    public UserManager getPlayerManager() {
        return playerManager;
    }

    public Database getConnection() {
        return sql;
    }

    public Database getDatabaseConnection() {
        return databaseConnection;
    }

}
