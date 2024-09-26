package xyz.sk1.bukkit.prisonextra.entity.minion.factory;

import org.bukkit.Location;
import xyz.sk1.bukkit.prisonextra.entity.minion.Minion;
import xyz.sk1.bukkit.prisonextra.entity.minion.type.MinionType;
import xyz.sk1.bukkit.prisonextra.player.User;
import xyz.sk1.bukkit.prisonextra.prisoner.Prisoner;
import xyz.sk1.bukkit.prisonextra.entity.minion.Miner;

import java.util.Objects;
import java.util.Optional;

public class MinionFactory {

    public Optional<Minion> createMinion(MinionType type, Location location, Prisoner owner) {

        if (Objects.requireNonNull(type) == MinionType.MINER) {
            return Optional.of(new Miner(location, owner));
        }

        return Optional.empty();
    }

}
