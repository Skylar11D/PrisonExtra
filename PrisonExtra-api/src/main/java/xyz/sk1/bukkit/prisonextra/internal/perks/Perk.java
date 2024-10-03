package xyz.sk1.bukkit.prisonextra.internal.perks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public abstract class Perk {

    protected BukkitTask runnable;

    abstract public void activate(Player player);

    public void deactivate(){
        if(this.runnable != null)
            this.runnable.cancel();
    }

}
