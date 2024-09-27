package xyz.sk1.bukkit.prisonextra.internal.storage;

import lombok.Getter;

import java.sql.Connection;
import java.util.List;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public abstract class Repository<T> {

    @Getter
    private Connection connection;
    private String tableName;

    protected Repository(Connection connection, String tableName) {
        this.connection = connection;
        this.tableName = tableName;
    }

    /**
     * fetch the object from the database
     * @param <K> the key to access the object
     */
    public abstract <K> T fetch(K identifier);

    /**
     * fetch all objects from the database as a list
     */
    public abstract List<T> fetchAll();

    /**
     * save the specified object to the database records
     * @param data the object to be saved
     */
    public abstract void save(T data);

    /**
     * delete a record of an object in the database
     * @param <K> the key to access the object
     */
    public abstract <K> void delete(K identifier);

    /**
     * checks for a table/collection of records
     * if the collection doesn't exist a new one is created
     */
    public abstract void validate();

    /**
     * delete all records from the database
     * <strong>must be done with cautious</strong>
     */
    public abstract void erase();

}
