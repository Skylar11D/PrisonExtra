package xyz.sk1.bukkit.prisonextra.executors.debug.subcommand;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.executors.Subcommand;
import xyz.sk1.bukkit.prisonextra.cosmetics.particles.HaloAura;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.player.User;
import xyz.sk1.bukkit.prisonextra.player.UserManager;

public class DebugSubSpawn implements Subcommand {

    @Override
    public void dispatch(Player sender, String[] args) {

        if(!args[1].equalsIgnoreCase("HaloAura")) {
            return;
        }

        UserManager userManager = (UserManager) Core.getInstance().getManagerRegistry().getType(ManagerType.PRISON);
        User user = userManager.get(sender);

        user.summonPerk(new HaloAura());


    }
}
