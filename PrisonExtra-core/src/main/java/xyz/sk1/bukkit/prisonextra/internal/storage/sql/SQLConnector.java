package xyz.sk1.bukkit.prisonextra.internal.storage.sql;

import com.google.gson.JsonObject;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jetbrains.annotations.NotNull;
import xyz.sk1.bukkit.prisonextra.internal.configuration.ConfigurationHandler;
import xyz.sk1.bukkit.prisonextra.internal.storage.DatabaseConnector;
import xyz.sk1.bukkit.prisonextra.internal.storage.Repository;
import xyz.sk1.bukkit.prisonextra.internal.storage.sql.repositories.MinionsRepository;
import xyz.sk1.bukkit.prisonextra.internal.storage.sql.repositories.RegionsRepository;
import xyz.sk1.bukkit.prisonextra.internal.storage.sql.repositories.UsersRepository;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

import java.sql.*;

public class SQLConnector implements DatabaseConnector {

    private ConfigurationHandler configurationHandler;
    private HikariDataSource dataSource;

    public SQLConnector(ConfigurationHandler jsonConfigHandler){
        this.configurationHandler = jsonConfigHandler;
    }

    @Override
    public void connect() {
        Utils.LOG.info("\nConnecting to the database..\n");

        Credentials result = this.getResult();


        this.init(result.hostname, result.port, result.database, result.autoconnect, result.username, result.passphrase);

        String regions = result.tables.get("regions").getAsString();
        String minions = result.tables.get("minions").getAsString();
        String prisoners = result.tables.get("prisoners").getAsString();

        Repository<?> regionRepository = new RegionsRepository(this.getConnection(), regions);
        Repository<?> usersRepository = new UsersRepository(this.getConnection(), minions);
        Repository<?> minionsRepository = new MinionsRepository(this.getConnection(), prisoners);

        regionRepository.validate();

        usersRepository.validate();

        minionsRepository.validate();

    }

    @NotNull
    private Credentials getResult() {
        JsonObject service = (JsonObject) configurationHandler.get("service");

        boolean autoconnect = service.get("autoconnection").getAsBoolean();
        String hostname = service.get("hostname").getAsString();
        int port = service.get("port").getAsInt();
        String username = service.get("username").getAsString();
        String passphrase = service.get("passphrase").getAsString();

        JsonObject databaseObj = service.getAsJsonObject("database");

        String database = databaseObj.get("name").getAsString();

        JsonObject tables = databaseObj.get("table").getAsJsonObject();

        return new Credentials(autoconnect, hostname, port, username, passphrase, database, tables);
    }

    private static class Credentials {
        public final boolean autoconnect;
        public final String hostname;
        public final int port;
        public final String username;
        public final String passphrase;
        public final String database;
        public final JsonObject tables;

        public Credentials(boolean autoconnect, String hostname, int port, String username,
                           String passphrase, String database, JsonObject config) {
            this.autoconnect = autoconnect;
            this.hostname = hostname;
            this.port = port;
            this.username = username;
            this.passphrase = passphrase;
            this.database = database;
            this.tables = config;
        }
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        if(dataSource != null)
            dataSource.close();
    }

    private void init(String hostname, int port, String database, boolean autoconnect, String username, String passphrase) {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://"+ hostname +":"+ port +"/"+ database +"?autoReconnect="+ autoconnect);
        config.setUsername(username);
        config.setPassword(passphrase);

        config.setLeakDetectionThreshold(5000);
        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(30*1000); // 30 seconds
        config.setIdleTimeout(1000*60*10); // 10 minutes
        config.setMaxLifetime(1000*60*30); // 30 minutes

        this.dataSource = new HikariDataSource(config);
    }

}
