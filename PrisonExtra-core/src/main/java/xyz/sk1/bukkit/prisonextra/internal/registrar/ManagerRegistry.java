package xyz.sk1.bukkit.prisonextra.internal.registrar;

import xyz.sk1.bukkit.prisonextra.manager.Manager;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;

import java.util.HashMap;
import java.util.Map;

public class ManagerRegistry {

    private Map<ManagerType, Manager> managers;

    public ManagerRegistry(){
        this.managers = new HashMap<>();
    }

    public void register(Manager manager) {
        if(managers.containsKey(manager))
            return;

        managers.put(manager.getType(), manager);
    }

    public void unregister(Manager manager) {
        managers.remove(manager.getType());
    }

    public Manager getManager(ManagerType type){
        return managers.get(type);
    }

}
