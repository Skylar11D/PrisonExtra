package xyz.sk1.bukkit.prisonextra.internal.storage;

import xyz.sk1.bukkit.prisonextra.utilities.Utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection extends SQL {
    private Connection conn;

    public SQLConnection(String host, String database, int port, String username, String password, String table) {
        super(host, database, port, username, password, table);
        String insertSQL =
                "CREATE TABLE " + getTable() + " " +
                        "IF NOT EXISTS (UUID VARCHAR(36) PRIMARY KEY, " +
                        "hasHouse BOOLEAN, " +
                        "hasMiner BOOLEAN, " +
                        "cloaks INT(55), " +
                        "particles INT(55)";

        try {
            Statement statement = getConnection().createStatement();

            statement.execute(insertSQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void connect() {

        synchronized (this){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                this.conn = DriverManager.getConnection("jdbc:mysql://"+getHost()+":"+getPort()+"/"+getDatabase(), getUsername(), getPassword());

                Utils.LOG.info("Connection to MySQL service was successfully established!");

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    protected Connection getConnection() {
        return conn;
    }

    @Override
    public void close() throws IOException {

        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
