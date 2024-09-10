package xyz.sk1.bukkit.prisonextra.player;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.managers.PrisonManager;
import xyz.sk1.bukkit.prisonextra.prisoner.Prisoner;
import xyz.sk1.bukkit.prisonextra.utilities.tasks.UserTask;

public class UserManager extends PrisonManager<Player, Prisoner, UserTask> {

    @Override
    protected Prisoner get(org.bukkit.entity.Player player) {
        return getPrisoners().keySet().stream().filter(
                p -> p.getPlayer() == player).findFirst().orElse(null
        );
    }

    @Override
    protected boolean checkPrisoner(Player player) {
        return getPrisoners().containsKey(player);
    }

    @Override
    protected void imprison(Player player) {
        getPrisoners().put(() -> player.getPlayer(), new UserTask());
    }

    @Override
    protected void release(Player player) {
        getPrisoners().remove(player);
    }

}
