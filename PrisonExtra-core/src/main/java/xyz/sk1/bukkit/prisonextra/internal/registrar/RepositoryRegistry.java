package xyz.sk1.bukkit.prisonextra.internal.registrar;


import xyz.sk1.bukkit.prisonextra.internal.storage.repository.Repository;
import xyz.sk1.bukkit.prisonextra.internal.storage.repository.RepositoryType;
import xyz.sk1.bukkit.prisonextra.registrar.Registry;

public class RepositoryRegistry extends Registry<RepositoryType, Repository<?>> {

    @Override
    public void register(RepositoryType key, Repository<?> value) {
        if(getRegistry().containsKey(key))
            return;

        getRegistry().put(key, value);
    }

    @Override
    public void unregister(RepositoryType key) {
        if (!getRegistry().containsKey(key))
            return;

        getRegistry().remove(key);
    }

    @Override
    public Repository<?> getType(RepositoryType key) {
        return getRegistry().get(key);
    }
}
