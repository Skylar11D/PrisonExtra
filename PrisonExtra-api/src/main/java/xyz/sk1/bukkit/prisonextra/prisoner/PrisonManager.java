package xyz.sk1.bukkit.prisonextra.prisoner;

import xyz.sk1.bukkit.prisonextra.manager.Manager;

import java.util.List;

/**
 * @Author <a href="https://www.github.com/user/skylar11d">Skylar</a>
 *
 * @param <P> The target to be imprisoned
 * @param <E> The prisoner object
 */
public interface PrisonManager<P, E> extends Manager {

    /**
     * Get a list of all registered prisoners
     */
    List<E> getPrisoners();

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
    boolean validate(P player);

    /**
     * Stores the player into the local cache as a prison
     * @param player the player to be imprisoned
     */
    void imprison(P player);

    /**
     * Release the prisoner from the local cache
     * @param player
     */
    void release(P player);

/*    *//**
     * get a list of minions of a prisoner
     * @param player
     * @return <code>List<Minion></code> object
     *//*
    List<M> getMinions(P player);

    *//**
     * get a specific minion of a prisoner
     * @param player
     * @return Optional object of <code>Minion</code>
     *//*
    Optional<Minion> getMinion(Player player, MinionType type);

    void registerMinion(Minion minion, P player);*/

}
