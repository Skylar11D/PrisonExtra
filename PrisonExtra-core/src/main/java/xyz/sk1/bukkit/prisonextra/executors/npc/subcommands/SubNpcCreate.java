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
            sender.sendMessage(Utils.colorize("&cInvalid argument"));
            return;
        }

        if(args[1].isEmpty()) {
            sender.sendMessage(Utils.colorize("&cThe name left empty!"));
            return;
        }

        if(args.length < 4){
            sender.sendMessage(Utils.colorize("&cInvalid arguments"));
            return;
        }

        subNpcType.dispatch(sender, args);

    }
}
