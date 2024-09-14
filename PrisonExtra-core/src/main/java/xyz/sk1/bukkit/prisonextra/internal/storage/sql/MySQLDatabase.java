package xyz.sk1.bukkit.prisonextra.internal.storage.sql;

import lombok.*;
import xyz.sk1.bukkit.prisonextra.internal.storage.Database;
import xyz.sk1.bukkit.prisonextra.utils.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

        try {
            //only one thread allowed
            synchronized (this){
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(connectionUrl, username, passphrase);

                Utils.LOG.info("Checking tables specified in (database.json)..");
                //createTables();

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

    private void createTables(String regionTable){

    }
}
