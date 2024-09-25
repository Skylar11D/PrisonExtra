package xyz.sk1.bukkit.prisonextra.internal.storage.sql;

import com.google.gson.JsonObject;
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

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionUrl, username, passphrase);

            Utils.LOG.info("Checking tables specified in file: database.json");

            JsonObject db = service.getAsJsonObject("database");
            JsonObject table = db.getAsJsonObject("table");
            JsonObject region = table.getAsJsonObject("regions");
            JsonObject minions = table.getAsJsonObject("minions");
            JsonObject prisoners = table.getAsJsonObject("prisoners");

            String rName = region.getAsString();

            String mName = minions.getAsString();

            String pName = prisoners.getAsString();

            Utils.LOG.info("[DEBUG] region table: " + rName);
            Utils.LOG.info("[DEBUG] minions table: " + mName);
            Utils.LOG.info("[DEBUG] player table: " + pName);

            validateRegionTable(rName);
            validateMinionTable(mName);
            validatePrisonersTable(pName);

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
                    " (owner VARCHAR(32), world VARCHAR(32), denyAll BOOLEAN ,deniedPlayers JSON, " +
                    "x1 DOUBLE, y1 DOUBLE, z1 DOUBLE, x2, DOUBLE, y2 DOUBLE, z2 DOUBLE)";

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
                    "(prisoner VARCHAR(32), x1 DOUBLE, y1 DOUBLE, z1 DOUBLE, x2, DOUBLE, y2 DOUBLE, z2 DOUBLE)";

            Statement statement = getConnection().createStatement();

            statement.execute(query);

            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
