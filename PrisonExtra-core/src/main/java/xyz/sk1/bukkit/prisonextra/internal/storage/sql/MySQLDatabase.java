package xyz.sk1.bukkit.prisonextra.internal.storage.sql;

import com.google.gson.JsonObject;
import lombok.*;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.internal.configuration.ConfigurationHandler;
import xyz.sk1.bukkit.prisonextra.internal.storage.Database;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

import java.sql.*;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class MySQLDatabase extends Database {

    private Connection connection;

    private final String hostname;
    private final String database;
    private final int port;
    private final String username;
    private final String passphrase;

    private final String minionTable;
    private final String regionTable;
    private final String cosmeticTable;

    @Override
    public void connect() {
        String connectionUrl = "jdbc:mysql://"+hostname+":"+port+"/"+database;
        ConfigurationHandler<JsonObject> ch = Core.getInstance().getDatabasecfg();

        try {
            //only one thread allowed
            synchronized (this){
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(connectionUrl, username, passphrase);

                Utils.LOG.info("Checking tables specified in (database.json)..");


                JsonObject service = ch.get("service");
                JsonObject db = service.getAsJsonObject("database");
                JsonObject table = db.getAsJsonObject("table");
                JsonObject region = table.getAsJsonObject("regions");
                JsonObject minions = table.getAsJsonObject("minions");
                JsonObject prisoners = table.getAsJsonObject("prisoners");

                String rName = region.getAsString();

                String mName = minions.getAsString();

                String pName = prisoners.getAsString();

                checkRegionTable(rName);
                checkMinionTable(mName);
                checkPrisonersTable(pName);
            }

        } catch (ClassNotFoundException | RuntimeException | SQLException e) {
            e.printStackTrace();
            Utils.LOG.warning("Failed to connect to the database, please define it in database.json");
        }

    }

    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkRegionTable(String regionTable){
        try {
            String query = "CREATE TABLE IF NOT EXISTS " + regionTable +
                    " (owner VARCHAR(32), world VARCHAR(32), denyAll BOOLEAN ,deniedPlayers JSON, " +
                    "x1 DOUBLE, y1 DOUBLE, z1 DOUBLE, x2, DOUBLE, y2 DOUBLE, z2 DOUBLE)";

            Statement statement = getConnection().createStatement();

            statement.execute(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkMinionTable(String minionTable){
        try {
            String query = "CREATE TABLE IF NOT EXISTS " + minionTable +
                    "(owner VARCHAR(32), duration BIGINT, rented BOOLEAN)";

            Statement statement = getConnection().createStatement();

            statement.execute(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkPrisonersTable(String pName) {
        try {
            String query = "CREATE TABLE IF NOT EXISTS " + pName +
                    "(prisoner VARCHAR(32), x1 DOUBLE, y1 DOUBLE, z1 DOUBLE, x2, DOUBLE, y2 DOUBLE, z2 DOUBLE)";

            Statement statement = getConnection().createStatement();

            statement.execute(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
