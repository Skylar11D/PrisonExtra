package xyz.sk1.bukkit.prisonextra.internal.storage.sql;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import xyz.sk1.bukkit.prisonextra.internal.configuration.ConfigurationHandler;
import xyz.sk1.bukkit.prisonextra.internal.storage.DatabaseConnector;
import xyz.sk1.bukkit.prisonextra.internal.storage.repository.Repository;
import xyz.sk1.bukkit.prisonextra.internal.storage.sql.repositories.MinionsRepository;
import xyz.sk1.bukkit.prisonextra.internal.storage.sql.repositories.RegionsRepository;
import xyz.sk1.bukkit.prisonextra.internal.storage.sql.repositories.UsersRepository;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

import java.sql.*;

public class SQLConnector implements DatabaseConnector {

    private final ConfigurationHandler<?> configurationHandler;
    private Connection connection;
    private Credentials result;
    private Repository<?> regionsRepository, usersRepository, minionsRepository;

    public SQLConnector(ConfigurationHandler<?> jsonConfigHandler){
        this.configurationHandler = jsonConfigHandler;
        this.result = this.getResult();
    }

    @Override
    public void connect() {
        Utils.LOG.info("\nConnecting to the database..\n");

        try {

            Class.forName("com.mysql.jdbc.Driver");

            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://"+result.HOST+":"+result.PORT+"/"+result.DATABASE+"?autoReconnect="+result.AUTO
            );

            Utils.LOG.fine("Connected to the database "+ result.DATABASE);

        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            Utils.LOG.info("failed to connect to the database");
        }

        String regions = result.TABLES.get("regions").getAsString();
        String minions = result.TABLES.get("minions").getAsString();
        String prisoners = result.TABLES.get("prisoners").getAsString();

        this.regionsRepository = new RegionsRepository(this.getConnection(), regions);
        this.usersRepository = new UsersRepository(this.getConnection(), minions);
        this.minionsRepository = new MinionsRepository(this.getConnection(), prisoners);

        this.regionsRepository.validate();

        this.usersRepository.validate();

        this.minionsRepository.validate();

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
        public final boolean AUTO;
        public final String HOST;
        public final int PORT;
        public final String USERNAME;
        public final String PASSPHRASE;
        public final String DATABASE;
        public final JsonObject TABLES;

        public Credentials(boolean autoConnect, String hostname, int port, String username,
                           String passphrase, String database, JsonObject config) {
            this.AUTO = autoConnect;
            this.HOST = hostname;
            this.PORT = port;
            this.USERNAME = username;
            this.PASSPHRASE = passphrase;
            this.DATABASE = database;
            this.TABLES = config;
        }
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public void close() {
        if(this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
