package xyz.sk1.bukkit.prisonextra;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import lombok.AccessLevel;
import lombok.Getter;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.FakePlayerManager;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.NpcManager;
import xyz.sk1.bukkit.prisonextra.events.listeners.PlayerInteractListener;
import xyz.sk1.bukkit.prisonextra.internal.PluginManager;
import xyz.sk1.bukkit.prisonextra.internal.cache.LRUCacheRegistry;
import xyz.sk1.bukkit.prisonextra.internal.configuration.ConfigurationHandler;
import xyz.sk1.bukkit.prisonextra.internal.configuration.factory.ConfigurationHandlerFactory;
import xyz.sk1.bukkit.prisonextra.internal.registrar.ManagerRegistry;
import xyz.sk1.bukkit.prisonextra.internal.storage.PDatabase;
import xyz.sk1.bukkit.prisonextra.internal.storage.factory.DatabaseFactory;
import xyz.sk1.bukkit.prisonextra.internal.storage.types.DatabaseType;
import xyz.sk1.bukkit.prisonextra.manager.Manager;
import xyz.sk1.bukkit.prisonextra.player.UserManager;
import xyz.sk1.bukkit.prisonextra.region.RegionManager;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;
import xyz.sk1.bukkit.prisonextra.housing.House;
import xyz.sk1.bukkit.prisonextra.housing.HouseManager;
import xyz.sk1.bukkit.prisonextra.utilities.factory.AbstractDatabaseFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 *
 * The control flow in this class is highly crucial, don't change the order
 */

@Getter
public class Core extends Base {

    private static volatile Core instance;

    private PluginManager pluginManager;
    @Getter(AccessLevel.PRIVATE)
    private Manager userManager;
    @Getter(AccessLevel.PRIVATE)
    private NpcManager<?> fakeplayerManager;
    @Getter(AccessLevel.PRIVATE)
    private RegionManager<House> regionManager;
    private ProtocolManager protocolManager;

    private ManagerRegistry managerRegistry;
    @SuppressWarnings("all")
    private LRUCacheRegistry lruCacheRegistry;

    private PDatabase PDatabase;

    private AbstractDatabaseFactory abstractDatabaseFactory;
    @Getter(AccessLevel.PRIVATE)
    private ConfigurationHandlerFactory configurationFactory;

    private ConfigurationHandler<?> settings;
    private ConfigurationHandler<?> databasecfg;

    @Override
    public void init() {
        instance = this;

        Utils.LOG.warning("This plugin only supports MySQL 5.7 service and above");

        Utils.LOG.info("Creating necessary caches..");
        lruCacheRegistry = new LRUCacheRegistry<>();
        lruCacheRegistry.createCache("regions", 500);
        lruCacheRegistry.createCache("fakeplayers", 4);


        this.saveResource("settings.yml", false);
        this.saveResource("database.json", false);

        configurationFactory = new ConfigurationHandlerFactory();
        settings = configurationFactory.createConfigHandler(new File(getDataFolder(), "settings.yml")).orElse(null);
        databasecfg = configurationFactory.createConfigHandler(new File(getDataFolder(), "database.json")).orElse(null);

        protocolManager.addPacketListener(new PlayerInteractListener());

        this.pluginManager = new PluginManager();
        this.managerRegistry = new ManagerRegistry();

        this.protocolManager = ProtocolLibrary.getProtocolManager();
        this.fakeplayerManager = new FakePlayerManager();

        this.userManager = new UserManager((FakePlayerManager) fakeplayerManager);
        this.regionManager = new HouseManager();

        this.managerRegistry.register(userManager);
        this.managerRegistry.register(regionManager);
        this.managerRegistry.register(fakeplayerManager);


        abstractDatabaseFactory = new DatabaseFactory();
        PDatabase = abstractDatabaseFactory.createDatabase(DatabaseType.MYSQL);

        PDatabase.connect();

        loadCaches();

        this.pluginManager.registerListeners("xyz.sk1.bukkit.prisonextra.events.listeners");
        this.pluginManager.registerExecutors("xyz.sk1.bukkit.prisonextra.executors");


    }

    private void loadCaches() {
        CompletableFuture.runAsync(() -> {
            try {
                this.userManager.start();
                this.regionManager.start();
                this.fakeplayerManager.start();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void fini() {

        this.fakeplayerManager.finish();

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
