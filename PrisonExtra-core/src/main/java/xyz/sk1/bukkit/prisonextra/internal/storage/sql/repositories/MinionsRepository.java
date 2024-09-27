package xyz.sk1.bukkit.prisonextra.internal.storage.sql.repositories;

import xyz.sk1.bukkit.prisonextra.entity.minion.Minion;
import xyz.sk1.bukkit.prisonextra.internal.storage.Repository;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

public class MinionsRepository extends Repository<Minion> {

    public MinionsRepository(Connection connection, String tableName) {
        super(connection, tableName);
    }

    @Override
    public <K> Minion fetch(K identifier) {
        return null;
    }

    @Override
    public List<Minion> fetchAll() {
        return Collections.emptyList();
    }

    @Override
    public void save(Minion data) {

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
