package xyz.sk1.bukkit.prisonextra.internal.storage.sql.repositories;

import xyz.sk1.bukkit.prisonextra.internal.storage.Repository;
import xyz.sk1.bukkit.prisonextra.region.Region;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public class RegionsRepository extends Repository<Region> {

    public RegionsRepository(Connection connection, String tableName) {
        super(connection, tableName);
    }

    @Override
    public <K> Region fetch(K identifier) {



        return null;
    }

    @Override
    public List<Region> fetchAll() {
        return Collections.emptyList();
    }

    @Override
    public void save(Region data) {

    }

    @Override
    public <K> void delete(K identifier) {

    }

    @Override
    public void validate() {

    }

    @Override
    public void erase() {

    }

}
