package xyz.sk1.bukkit.prisonextra.internal.registrar;

import xyz.sk1.bukkit.prisonextra.manager.Manager;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.registrar.Registry;

public class ManagerRegistry extends Registry<ManagerType, Manager> {

    @Override
    public void register(ManagerType type, Manager manager) {
        if(getRegistry().containsKey(type))
            return;

        getRegistry().put(type, manager);
    }

    @Override
    public void unregister(ManagerType type) {
        if(!getRegistry().containsKey(type))
            return;

        getRegistry().remove(type);
    }

    @Override
    public Manager getType(ManagerType key) {
        return getRegistry().get(key);
    }
}
