package xyz.sk1.bukkit.prisonextra.internal.storage.sql;

import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.internal.configuration.ConfigurationHandler;
import xyz.sk1.bukkit.prisonextra.internal.configuration.JsonConfigurationHandler;
import xyz.sk1.bukkit.prisonextra.internal.configuration.YamlConfigurationHandler;
import xyz.sk1.bukkit.prisonextra.internal.storage.DatabaseConnector;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

import java.sql.*;

public class MySQLDatabase extends DatabaseConnector {

    private Connection connection;

    private ConfigurationHandler configurationHandler;

    public MySQLDatabase(ConfigurationHandler jsonConfigHandler){
        this.configurationHandler = jsonConfigHandler;
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

        try {

            String connectionUrl = "jdbc:mysql://"+hostname+":"+port+"/"+database+"?autoReconnect="+autoconnect;

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionUrl, username, passphrase);

            JsonObject table = databaseObj.get("table").getAsJsonObject();
            String regions = table.get("regions").getAsString();
            String minions = table.get("minions").getAsString();
            String prisoners = table.get("prisoners").getAsString();

            validateRegionTable(regions);
            validateMinionTable(minions);
            validatePrisonersTable(prisoners);

            Bukkit.getServer().getConsoleSender().sendMessage(Utils.colorize("&a&lCONNECTED TO " + database + " DATABASE"));

        } catch (ClassNotFoundException | RuntimeException | SQLException e) {
            e.printStackTrace();
            Utils.LOG.warning("Failed to connect to the database, please define it in database.json");
        }

    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateRegionTable(String regionTable){
        try {
            String query = "CREATE TABLE IF NOT EXISTS " + regionTable +
                    " (Owner varchar(32), World varchar(32), DenyAll tinyint(1), DeniedPlayers json, x1 double(8, 3), y1 double(8, 3), z1 double(8, 3), x2 double(8, 3), y2 double(8, 3), z2 double(8, 3))";

            Statement statement = getConnection().createStatement();

            statement.execute(query);

            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateMinionTable(String minionTable){
        try {
            String query = "CREATE TABLE IF NOT EXISTS " + minionTable +
                    "(owner VARCHAR(32), duration BIGINT, rented BOOLEAN)";

            Statement statement = getConnection().createStatement();

            statement.execute(query);

            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void validatePrisonersTable(String pName) {
        try {
            String query = "CREATE TABLE IF NOT EXISTS " + pName +
                    "(prisoner VARCHAR(32), x1 double(8, 3), y1 double(8, 3), z1 double(8, 3), x2 double(8, 3), y2 double(8, 3), z2 double(8, 3))";

            Statement statement = getConnection().createStatement();

            statement.execute(query);

            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
