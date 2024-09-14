package xyz.sk1.bukkit.prisonextra.player;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.prisoner.PrisonManager;
import xyz.sk1.bukkit.prisonextra.prisoner.Prisoner;
import xyz.sk1.bukkit.prisonextra.utilities.tasks.PrisonTask;
import xyz.sk1.bukkit.prisonextra.utils.tasks.UserTask;

import java.util.*;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public class UserManager implements PrisonManager<Player, Prisoner, PrisonTask> {

    private Map<Prisoner, List<PrisonTask>> prisoners;
    private List<PrisonTask> prisonTasks;

    public UserManager(){
        this.prisoners = new HashMap<>();
        this.prisonTasks = new ArrayList<>();
    }

    @Override
    public Map<Prisoner, List<PrisonTask>> getPrisoners() {
        return this.prisoners;
    }

    @Override
    public Prisoner get(Player player) {
        return getPrisoners().keySet().stream().filter(
                p -> p.getPlayer() == player).findFirst().orElse(null);
    }

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
        }, prisonTasks);
    }

    @Override
    public void release(Player player) {
        getPrisoners().remove(player);
    }


    @Override
    public void load() {



    }

    @Override
    public ManagerType getType() {
        return ManagerType.PRISON;
    }
}
