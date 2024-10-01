package xyz.sk1.bukkit.prisonextra.internal.storage.sql.repositories;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import xyz.sk1.bukkit.prisonextra.housing.factory.RegionFactory;
import xyz.sk1.bukkit.prisonextra.internal.storage.repository.Repository;
import xyz.sk1.bukkit.prisonextra.region.Region;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public class RegionsRepository extends Repository<Region> {

    private final RegionFactory regionFactory;

    public RegionsRepository(Connection connection, String tableName) {
        super(connection, tableName);
        this.regionFactory = new RegionFactory();
    }

    @Override
    public <K> Optional<Region> fetch(K identifier) {

        String query = ("SELECT * FROM " + getTableName() + " WHERE 'Owner'=?");

        try(PreparedStatement statement = getConnection().prepareStatement(query)) {

            statement.setString(1, (String) identifier);

            ResultSet results = statement.executeQuery();

            String owner = results.getString(1);
            String world = results.getString(2);

            double minX = results.getDouble(3);
            double minY = results.getDouble(4);
            double minZ = results.getDouble(5);

            double maxX = results.getDouble(6);
            double maxY = results.getDouble(7);
            double maxZ = results.getDouble(8);

            Location corner1 = new Location(Bukkit.getWorld(world), minX, minY, minZ);
            Location corner2 = new Location(Bukkit.getWorld(world), maxX, maxY, maxZ);

            return Optional.of(regionFactory.createRegion(corner1, corner2, owner));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Region> fetchAll() {
        List<Region> houses = new ArrayList<>();

        String query = ("SELECT * FROM "+getTableName());

        try(Statement statement = getConnection().createStatement()) {

            ResultSet results = statement.executeQuery(query);

            while (results.next()){

                String owner = results.getString(1);
                String world = results.getString(2);

                double minX = results.getDouble(3);
                double minY = results.getDouble(4);
                double minZ = results.getDouble(5);

                double maxX = results.getDouble(6);
                double maxY = results.getDouble(7);
                double maxZ = results.getDouble(8);

                Location corner1 = new Location(Bukkit.getWorld(world), minX, minY, minZ);
                Location corner2 = new Location(Bukkit.getWorld(world), maxX, maxY, maxZ);

                houses.add(regionFactory.createRegion(corner1, corner2, owner));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return houses;
    }

    @Override
    public void save(Region data) {

        String query = ("INSERT INTO "+getTableName()+" VALUES (?, ?, ?, ?, ?, ?, ?)");

        try(PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setString(1, data.getName());

            statement.setDouble(2, data.getMinX());
            statement.setDouble(3, data.getMinY());
            statement.setDouble(4, data.getMinZ());

            statement.setDouble(5, data.getMaxX());
            statement.setDouble(6, data.getMaxY());
            statement.setDouble(7, data.getMaxZ());

            statement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public <K> void delete(K identifier) {

        String query = ("DELETE FROM " + getTableName() + " WHERE 'Owner'=?");

        try(PreparedStatement statement = getConnection().prepareStatement(query)) {

            statement.setString(1, (String) identifier);

            statement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void validate() {
        Utils.LOG.warning("table name: "+getTableName());

        String query = "CREATE TABLE IF NOT EXISTS "+getTableName()+" (" +
                "Owner varchar(32), " +
                "World varchar(32), " +
                "MinX DECIMAL(8, 2), " +
                "MinY DECIMAL(8, 2), " +
                "MinZ DECIMAL(8, 2), " +
                "MaxX DECIMAL(8, 2), " +
                "MaxY DECIMAL(8, 2), " +
                "MaxZ DECIMAL(8, 2));";

        Utils.LOG.warning(getConnection().toString());

        try(Statement statement = getConnection().createStatement()) {

            statement.execute(query);

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void erase() {

        String query = ("TRUNCATE TABLE " + getTableName());

        try(Statement statement = getConnection().createStatement()) {

            statement.executeQuery(query);

            Utils.LOG.warning("data from table "+getTableName()+" has permanently been deleted");

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

}
