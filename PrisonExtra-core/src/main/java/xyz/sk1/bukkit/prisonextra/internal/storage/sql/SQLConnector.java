package xyz.sk1.bukkit.prisonextra.internal.storage.sql;

import com.google.gson.JsonObject;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
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

    private Repository regionRepository;
    private Repository usersRepository;
    private Repository minionsRepository;

    public SQLConnector(ConfigurationHandler jsonConfigHandler){
        this.configurationHandler = jsonConfigHandler;

        this.regionRepository = new RegionsRepository(this.getConnection(), "regions");
        this.usersRepository = new UsersRepository(this.getConnection(), "users");
        this.minionsRepository = new MinionsRepository(this.getConnection(), "minions");
    }

    @Override
    public void connect() {
        Utils.LOG.info("\nConnecting to the database..\n");

        JsonObject service = (JsonObject) configurationHandler.get("service");

        boolean autoconnect = service.get("autoconnection").getAsBoolean();
        String hostname = service.get("hostname").getAsString();
        int port = service.get("port").getAsInt();
        String username = service.get("username").getAsString();
        String passphrase = service.get("passphrase").getAsString();

        JsonObject databaseObj = service.getAsJsonObject("database");

        String database = databaseObj.get("name").getAsString();

        JsonObject table = databaseObj.get("table").getAsJsonObject();

        String regions = table.get("regions").getAsString();
        String minions = table.get("minions").getAsString();
        String prisoners = table.get("prisoners").getAsString();


        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://"+hostname+":"+port+"/"+database+"?autoReconnect="+autoconnect);
        config.setUsername(username);
        config.setPassword(passphrase);

        config.setLeakDetectionThreshold(5000);
        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(30*1000); // 30 seconds
        config.setIdleTimeout(1000*60*10); // 10 minutes
        config.setMaxLifetime(1000*60*30); // 30 minutes

        this.dataSource = new HikariDataSource(config);


        regionRepository.validate();

        usersRepository.validate();

        minionsRepository.validate();

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

}
