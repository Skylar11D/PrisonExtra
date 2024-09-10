package xyz.sk1.bukkit.prisonextra;

import xyz.sk1.bukkit.prisonextra.internal.PluginManager;
import xyz.sk1.bukkit.prisonextra.internal.storage.SQL;
import xyz.sk1.bukkit.prisonextra.player.UserManager;

import java.io.IOException;

public class Core extends Base {

    private static volatile Core instance;
    private PluginManager pluginManager;
    private UserManager playerManager;
    private SQL sql;

    @Override
    public void init() {
        instance = this;

        this.pluginManager = new PluginManager();
        this.pluginManager.registerListeners("xyz.sk1.bukkit.prisonextra.listeners");

        this.playerManager = new UserManager();
        //this.sql = new MySQL();

    }

    @Override
    public void fini() {

        try {
            sql.close();
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

    public SQL getConnection() {
        return sql;
    }
}
