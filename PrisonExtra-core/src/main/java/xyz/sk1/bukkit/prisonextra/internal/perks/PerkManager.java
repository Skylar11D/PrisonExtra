package xyz.sk1.bukkit.prisonextra.internal.perks;

import xyz.sk1.bukkit.prisonextra.manager.Manager;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;

public class PerkManager implements Manager {


    @Override
    public void load() {

        //no load for perks

    }

    @Override
    public ManagerType getType() {
        return ManagerType.PERK;
    }
}
