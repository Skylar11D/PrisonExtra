package xyz.sk1.bukkit.prisonextra.executors.npc.subcommands;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.executors.Subcommand;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.player.UserManager;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

public class SubNpcCreate implements Subcommand {

    private SubNpcType subNpcType;

    public SubNpcCreate(){
        this.subNpcType = new SubNpcType();
    }

    @Override
    public void dispatch(Player sender, String[] args) {

        if(!args[0].equalsIgnoreCase("create")) {
            sender.sendMessage(Utils.colorize("&cInvalid first argument!"));
            UserManager manager = (UserManager) Core.getInstance().getManagerRegistry().getManager(ManagerType.PRISON);
            manager.get(sender).getPlayer().sendMessage(Utils.colorize("&c[DEBUG] &aYou are a prisoner!"));
            return;
        }

        if(args[1].isEmpty()) {
            sender.sendMessage(Utils.colorize("&cThe name left empty!"));
            return;
        }

        Core.getInstance().getSettings().set("npcs.attributes.name", args[1]);

        subNpcType.dispatch(sender, args);

    }
}
