package xyz.sk1.bukkit.prisonextra.internal.storage;

import lombok.*;

import java.io.IOException;
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
            //needs to be
            synchronized (this){
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(connectionUrl, username, passphrase);
            }

        } catch (ClassNotFoundException | RuntimeException | SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
