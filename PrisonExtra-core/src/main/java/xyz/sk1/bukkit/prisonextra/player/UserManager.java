package xyz.sk1.bukkit.prisonextra.player;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPCObserver;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.factory.NPCFactory;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.entity.minion.Minion;
import xyz.sk1.bukkit.prisonextra.entity.minion.type.MinionType;
import xyz.sk1.bukkit.prisonextra.prisoner.PrisonManager;
import xyz.sk1.bukkit.prisonextra.prisoner.Prisoner;
import xyz.sk1.bukkit.prisonextra.utilities.tasks.PrisonTask;

import java.util.*;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public class UserManager implements PrisonManager<Player, Prisoner, Minion> {

    private final Map<Prisoner, List<Minion>> prisoners;
    @SuppressWarnings("all")
    private final List<PrisonTask> prisonTasks;
    private NPCFactory npcFactory;

    public UserManager(){
        this.prisoners = new HashMap<>();
        this.prisonTasks = new ArrayList<>();
        this.npcFactory = new NPCFactory();
    }

    @Override
    public Map<Prisoner, List<Minion>> getPrisoners() {
        return this.prisoners;
    }

    @Override
    public User get(Player player) {
        return (User) getPrisoners().keySet().stream().filter(
                p -> p.getPlayer() == player).findFirst().orElse(null);
    }

    public NPCObserver[] toObservers(){
        return (NPCObserver[])getPrisoners().keySet().stream().toArray();
    }

    @SuppressWarnings("all")
    @Override
    public boolean checkPrisoner(Player player) {
        return getPrisoners().containsKey(player);
    }

    @Override
    public void imprison(Player player) {
        getPrisoners().put(new User() {
            @Override
            public Player getPlayer() {
                return player;
            }
        }, Collections.emptyList());
    }

    @SuppressWarnings("all")
    @Override
    public void release(Player player) {
        getPrisoners().remove(player);
    }

    @Override
    public List<Minion> getMinions(Player player) {
        return prisoners.get(player);
    }

    @Override
    public Optional<Minion> getMinion(Player player, MinionType type) {

        return Optional.of(prisoners.get(player).stream()
                .filter(m -> m.getType() == type)
                .findFirst().get());
    }

    @Override
    public void registerMinion(Minion minion, Player player) {
        prisoners.get(player).add(minion);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void load() {

    }

    @Override
    public ManagerType getType() {
        return ManagerType.PRISON;
    }
}
