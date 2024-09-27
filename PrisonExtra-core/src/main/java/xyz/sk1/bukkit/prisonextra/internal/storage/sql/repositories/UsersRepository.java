package xyz.sk1.bukkit.prisonextra.internal.storage.sql.repositories;

import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.internal.storage.repository.Repository;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.player.User;
import xyz.sk1.bukkit.prisonextra.player.UserManager;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UsersRepository extends Repository<User> {

    private final UserManager userManager;

    public UsersRepository(Connection connection, String tableName) {
        super(connection, tableName);
        this.userManager = (UserManager)Core.getInstance().getManagerRegistry().getType(ManagerType.PRISON);
    }

    @Override
    public <K> Optional<User> fetch(K identifier) {



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
