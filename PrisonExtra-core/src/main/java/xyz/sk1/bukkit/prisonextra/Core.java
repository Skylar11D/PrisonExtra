package xyz.sk1.bukkit.prisonextra;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import xyz.sk1.bukkit.prisonextra.internal.PluginManager;
import xyz.sk1.bukkit.prisonextra.internal.cache.LRUCacheRegistry;
import xyz.sk1.bukkit.prisonextra.internal.configuration.ConfigManager;
import xyz.sk1.bukkit.prisonextra.internal.configuration.ConfigurationFactory;
import xyz.sk1.bukkit.prisonextra.internal.configuration.type.ConfigurationManager;
import xyz.sk1.bukkit.prisonextra.internal.registrar.ManagerRegistry;
import xyz.sk1.bukkit.prisonextra.internal.storage.Database;
import xyz.sk1.bukkit.prisonextra.internal.storage.types.DatabaseType;
import xyz.sk1.bukkit.prisonextra.player.UserManager;
import xyz.sk1.bukkit.prisonextra.prisoner.PrisonManager;
import xyz.sk1.bukkit.prisonextra.region.RegionManager;
import xyz.sk1.bukkit.prisonextra.utilities.factory.AbstractConfigurationFactory;
import xyz.sk1.bukkit.prisonextra.utils.Utils;
import xyz.sk1.bukkit.prisonextra.internal.storage.factory.DatabaseFactory;
import xyz.sk1.bukkit.prisonextra.utils.housing.House;
import xyz.sk1.bukkit.prisonextra.utils.housing.HouseManager;
import xyz.sk1.bukkit.prisonextra.utilities.factory.AbstractDatabaseFactory;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

@Getter
public class Core extends Base {

    private static volatile Core instance;

    private PluginManager pluginManager;
    private PrisonManager userManager;
    private RegionManager<House> regionManager;
    private ConfigurationManager configurationManager;

    private ManagerRegistry managerRegistry;
    private LRUCacheRegistry lruCacheRegistry;

    private Database database;

    private AbstractDatabaseFactory abstractDatabaseFactory;
    private AbstractConfigurationFactory abstractConfigFactory;

    private FileConfiguration settings;

    @Override
    public void init() {
        instance = this;

        abstractDatabaseFactory = new DatabaseFactory();
        database = abstractDatabaseFactory.createDatabase(DatabaseType.MYSQL);

        abstractConfigFactory = new ConfigurationFactory();
        this.settings = abstractConfigFactory.createConfig(this, "settings.yml");

        Utils.LOG.info("Connecting to the database..");
        database.connect();

        lruCacheRegistry = new LRUCacheRegistry<>();
        lruCacheRegistry.createCache("regions", 500);

        this.pluginManager = new PluginManager();
        this.managerRegistry = new ManagerRegistry();

        this.userManager = new UserManager();
        this.regionManager = new HouseManager();
        this.configurationManager = new ConfigManager(settings);

        this.managerRegistry.register(userManager);
        this.managerRegistry.register(regionManager);
        this.managerRegistry.register(configurationManager);

        try {

            Utils.LOG.info("Loading all players to the cache..");
            this.userManager.start();

            Utils.LOG.info("Loading all regions to the cache..");
            this.regionManager.start();

        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void fini() {

        try {
            Utils.LOG.info("Closing any connection to the database..");
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

}
