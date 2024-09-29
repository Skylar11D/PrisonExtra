package xyz.sk1.bukkit.prisonextra.internal.perks;

import org.bukkit.entity.Player;

public interface Perk {

    void activate(Player player);

    void deactivate();

}
