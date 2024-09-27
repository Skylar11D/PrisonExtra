package xyz.sk1.bukkit.prisonextra.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NpcObserver;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.FakePlayerManager;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.prisoner.PrisonManager;
import xyz.sk1.bukkit.prisonextra.prisoner.Prisoner;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public class UserManager implements PrisonManager<Player, Prisoner> {

    private final List<Prisoner> prisoners;
    private final FakePlayerManager fakePlayerManager;

    public UserManager(FakePlayerManager npcmgr){
        this.prisoners = new ArrayList<>();
        this.fakePlayerManager = npcmgr;
    }

    @Override
    public List<Prisoner> getPrisoners() {
        return this.prisoners;
    }

    @Override
    public User get(Player player) {
        return (User) prisoners.stream().filter(
                p -> p.getHandle() == player).findFirst().orElse(null);
    }

    @SuppressWarnings("all")
    public NpcObserver[] toObservers(){
        return (NpcObserver[]) prisoners.toArray();
    }

    @SuppressWarnings("all")
    @Override
    public boolean validate(Player player) {
        return prisoners.contains(player);
    }

    @Override
    public void imprison(Player player) {
        getPrisoners().add(new User() {
            @Override
            public Player getHandle() {
                return player;
            }
        });

        this.fakePlayerManager.registerObserver(new User() {
            @Override
            public Player getHandle() {
                return player;
            }
        });
    }

    @SuppressWarnings("all")
    @Override
    public void release(Player player) {
        this.fakePlayerManager.removeObserver(get(player));
        prisoners.remove(player);
    }

/*    @Override
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
    }*/

    @Override
    public void load() {
        Utils.LOG.info("Loading players into the cache...");
        CompletableFuture.runAsync(() -> {
            Bukkit.getOnlinePlayers().forEach(this::imprison);
        });
    }

    @Override
    public ManagerType getType() {
        return ManagerType.PRISON;
    }
}
