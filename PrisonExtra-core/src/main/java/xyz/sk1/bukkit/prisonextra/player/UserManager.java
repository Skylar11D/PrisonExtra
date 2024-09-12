package xyz.sk1.bukkit.prisonextra.player;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.prisoner.PrisonManager;
import xyz.sk1.bukkit.prisonextra.prisoner.Prisoner;
import xyz.sk1.bukkit.prisonextra.utils.tasks.UserTask;

import java.util.Collections;
import java.util.Map;

public class UserManager implements PrisonManager<Player, Prisoner, UserTask> {

    public UserManager(){

    }

    @Override
    public Map<Prisoner, UserTask> getPrisoners() {
        return Collections.emptyMap();
    }

    @Override
    public Prisoner get(Player player) {
        return getPrisoners().keySet().stream().filter(
                p -> p.getPlayer() == player).findFirst().orElse(null
        );
    }

    @Override
    public boolean checkPrisoner(Player player) {
        return getPrisoners().containsKey(player);
    }

    @Override
    public void imprison(Player player) {
        getPrisoners().put(() -> player.getPlayer(), new UserTask());
    }

    @Override
    public void release(Player player) {
        getPrisoners().remove(player);
    }


    @Override
    public void load() {

    }
}
