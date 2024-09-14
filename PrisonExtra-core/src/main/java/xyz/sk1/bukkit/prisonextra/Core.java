package xyz.sk1.bukkit.prisonextra;

import lombok.Getter;
import xyz.sk1.bukkit.prisonextra.internal.PluginManager;
import xyz.sk1.bukkit.prisonextra.internal.cache.LRUCacheRegistry;
import xyz.sk1.bukkit.prisonextra.internal.registrar.ManagerRegistry;
import xyz.sk1.bukkit.prisonextra.internal.storage.Database;
import xyz.sk1.bukkit.prisonextra.internal.storage.types.DatabaseType;
import xyz.sk1.bukkit.prisonextra.player.UserManager;
import xyz.sk1.bukkit.prisonextra.prisoner.PrisonManager;
import xyz.sk1.bukkit.prisonextra.region.RegionManager;
import xyz.sk1.bukkit.prisonextra.utils.cosmetics.CosmeticsManager;
import xyz.sk1.bukkit.prisonextra.internal.storage.factory.DatabaseFactory;
import xyz.sk1.bukkit.prisonextra.utils.housing.HouseManager;
import xyz.sk1.bukkit.prisonextra.utilities.factory.AbstractDatabaseFactory;

import java.io.IOException;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

@Getter
public class Core extends Base {

    private static volatile Core instance;
    private PluginManager pluginManager;
    private PrisonManager userManager;
    private RegionManager regionManager;
    private CosmeticsManager cosmeticsManager;

    private ManagerRegistry managerRegistry;
    private LRUCacheRegistry lruCacheRegistry;

    private Database database;
    private AbstractDatabaseFactory abstractDatabaseFactory;

    @Override
    public void init() {
        instance = this;

        abstractDatabaseFactory = new DatabaseFactory();
        database = abstractDatabaseFactory.createDatabase(DatabaseType.MYSQL);
        database.connect();

        lruCacheRegistry = new LRUCacheRegistry<>();
        lruCacheRegistry.createCache("regions", 1000);

        this.pluginManager = new PluginManager();
        this.managerRegistry = new ManagerRegistry();

        this.userManager = new UserManager();
        this.regionManager = new HouseManager();

        this.managerRegistry.register(userManager);
        this.managerRegistry.register(regionManager);


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

}
