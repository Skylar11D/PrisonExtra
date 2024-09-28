package xyz.sk1.bukkit.prisonextra.internal.perks.particles;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.internal.features.perks.Perk;

import java.util.function.Supplier;

public class LavaRing implements Perk {

    @Override
    public void activate(Supplier<Player> supplier) {

        Player player = supplier.get();

        if(player == null)
            return;



    }

    @Override
    public void deactivate() {

    }
}
