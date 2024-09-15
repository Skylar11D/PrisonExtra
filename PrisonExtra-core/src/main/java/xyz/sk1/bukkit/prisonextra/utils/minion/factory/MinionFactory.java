package xyz.sk1.bukkit.prisonextra.utils.minion.factory;

import org.bukkit.Location;
import xyz.sk1.bukkit.prisonextra.minion.Minion;
import xyz.sk1.bukkit.prisonextra.minion.type.MinionType;
import xyz.sk1.bukkit.prisonextra.player.User;
import xyz.sk1.bukkit.prisonextra.prisoner.Prisoner;
import xyz.sk1.bukkit.prisonextra.utils.minion.Miner;

import java.util.Objects;
import java.util.Optional;

public class MinionFactory {

    public Optional<Minion<?>> createMinion(MinionType type, Location location, Prisoner prisoner) {

        if (Objects.requireNonNull(type) == MinionType.MINER) {
            return Optional.of(new Miner(location, (User) prisoner));
        }

        return Optional.empty();
    }

}
