package xyz.sk1.bukkit.prisonextra.internal.features.perks;

import org.bukkit.entity.Player;
import java.util.function.Supplier;

public interface Perk {

    void activate(Supplier<Player> supplier);

    void deactivate();

}
