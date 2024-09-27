package xyz.sk1.bukkit.prisonextra.internal.storage.sql.repositories;

import xyz.sk1.bukkit.prisonextra.internal.storage.Repository;
import xyz.sk1.bukkit.prisonextra.player.User;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

public class UsersRepository extends Repository<User> {

    public UsersRepository(Connection connection, String tableName) {
        super(connection, tableName);
    }

    @Override
    public <K> User fetch(K identifier) {
        return null;
    }

    @Override
    public List<User> fetchAll() {
        return Collections.emptyList();
    }

    @Override
    public void save(User data) {

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
