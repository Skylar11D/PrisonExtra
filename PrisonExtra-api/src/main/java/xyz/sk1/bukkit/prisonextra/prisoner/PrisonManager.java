package xyz.sk1.bukkit.prisonextra.prisoner;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.manager.Manager;
import xyz.sk1.bukkit.prisonextra.minion.Minion;
import xyz.sk1.bukkit.prisonextra.minion.type.MinionType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author <a href="https://www.github.com/user/skylar11d">Skylar</a>
 *
 * @param <P> The target to be imprisoned
 * @param <E> The prisoner object
 * @param <M> Represents the prisoner's minions
 */
public interface PrisonManager<P, E, M extends Minion<?>> extends Manager {

    /**
     * Get a list of all registered prisoners
     */
    Map<E, List<M>> getPrisoners();

    /**
     * Get a specific prisoner as a player
     * @param player
     * @return <code>Prisoner</code> Object
     */
    E get(P player);

    /**
     * Checks whether the specified target player is a prisoner or not
     * @param player
     * @return true/false
     */
    boolean checkPrisoner(P player);

    void imprison(P player);

    void release(P player);

    /**
     * get a list of minions of a prisoner
     * @param player
     * @return <code>List<Minion></code> object
     */
    List<M> getMinions(P player);

    /**
     * get a specific minion of a prisoner
     * @param player
     * @return Optional object of <code>Minion</code>
     */
    Optional<Minion<?>> getMinion(Player player, MinionType type);

    void registerMinion(Minion<?> minion, P player);

}
