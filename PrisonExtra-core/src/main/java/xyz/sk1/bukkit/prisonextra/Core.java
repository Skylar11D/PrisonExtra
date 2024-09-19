package xyz.sk1.bukkit.prisonextra;

import lombok.AccessLevel;
import lombok.Getter;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.FakePlayerManager;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.NPCManager;
import xyz.sk1.bukkit.prisonextra.internal.PluginManager;
import xyz.sk1.bukkit.prisonextra.internal.cache.LRUCacheRegistry;
import xyz.sk1.bukkit.prisonextra.internal.configuration.ConfigurationHandler;
import xyz.sk1.bukkit.prisonextra.internal.configuration.factory.ConfigurationHandlerFactory;
import xyz.sk1.bukkit.prisonextra.internal.registrar.ManagerRegistry;
import xyz.sk1.bukkit.prisonextra.internal.storage.PDatabase;
import xyz.sk1.bukkit.prisonextra.internal.storage.types.DatabaseType;
import xyz.sk1.bukkit.prisonextra.manager.Manager;
import xyz.sk1.bukkit.prisonextra.player.UserManager;
import xyz.sk1.bukkit.prisonextra.region.RegionManager;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;
import xyz.sk1.bukkit.prisonextra.internal.storage.factory.DatabaseFactory;
import xyz.sk1.bukkit.prisonextra.utils.housing.House;
import xyz.sk1.bukkit.prisonextra.utils.housing.HouseManager;
import xyz.sk1.bukkit.prisonextra.utilities.factory.AbstractDatabaseFactory;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

@Getter
public class Core extends Base {

    private static volatile Core instance;

    private PluginManager pluginManager;
    @SuppressWarnings("all")
    @Getter(AccessLevel.PRIVATE)
    private Manager userManager;
    @Getter(AccessLevel.PRIVATE)
    private NPCManager fakeplayerManager;
    @Getter(AccessLevel.PRIVATE)
    private RegionManager<House> regionManager;

    private ManagerRegistry managerRegistry;
    @SuppressWarnings("all")
    private LRUCacheRegistry lruCacheRegistry;

    private PDatabase PDatabase;

    private AbstractDatabaseFactory abstractDatabaseFactory;
    @Getter(AccessLevel.PRIVATE)
    private ConfigurationHandlerFactory configurationFactory;

    @Getter(AccessLevel.PRIVATE)
    private File settingsFile;
    @Getter(AccessLevel.PRIVATE)
    private File databaseFile;
    private ConfigurationHandler settings;
    private ConfigurationHandler databasecfg;

    @Override
    public void init() {
        instance = this;

        Utils.LOG.warning("This plugin only supports MySQL 5.7 service and above");

        this.pluginManager = new PluginManager();
        this.managerRegistry = new ManagerRegistry();

        this.userManager = new UserManager();
        this.regionManager = new HouseManager();

        this.fakeplayerManager = new FakePlayerManager();

        this.managerRegistry.register(userManager);
        this.managerRegistry.register(regionManager);
        this.managerRegistry.register(fakeplayerManager);

        try {

            Utils.LOG.info("Loading all players to the cache..");
            this.userManager.start();

            Utils.LOG.info("Loading all regions to the cache..");
            this.regionManager.start();

            Utils.LOG.info("Loading all npcs to the cache..");
            this.fakeplayerManager.start();

        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        abstractDatabaseFactory = new DatabaseFactory();
        PDatabase = abstractDatabaseFactory.createDatabase(DatabaseType.MYSQL);

        settingsFile = new File(getDataFolder(), "settings.yml");
        databaseFile = new File(getDataFolder(), "database.json");

        this.saveResource("settings.yml", false);
        this.saveResource("database.json", false);

        configurationFactory = new ConfigurationHandlerFactory();
        settings = configurationFactory.createConfigHandler(settingsFile).orElse(null);
        databasecfg = configurationFactory.createConfigHandler(databaseFile).orElse(null);

        Utils.LOG.info("Connecting to the database..");
        //PDatabase.connect();

        Utils.LOG.info("Creating necessary caches..");
        lruCacheRegistry = new LRUCacheRegistry<>();
        lruCacheRegistry.createCache("regions", 500);
        lruCacheRegistry.createCache("fakeplayers", 4);

        this.pluginManager.registerListeners("xyz.sk1.bukkit.prisonextra.listeners");


    }

    @Override
    public void fini() {

        try {
            Utils.LOG.info("Closing any connection to the database..");
            PDatabase.close();
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
