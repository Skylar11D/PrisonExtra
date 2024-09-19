package xyz.sk1.bukkit.prisonextra.executors.npc.subcommands;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.executors.Subcommand;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

public class SubNpcType implements Subcommand {

    @Override
    public void dispatch(Player sender, String[] args) {

        if(!args[2].equalsIgnoreCase("type")) {
            sender.sendMessage(Utils.colorize("&cInvalid third argument!"));
            return;
        }
        if(args[3].isEmpty()){
            sender.sendMessage(Utils.colorize("&cInvalid type!"));
            return;
        }

        if(!args[3].equalsIgnoreCase("general")){
            sender.sendMessage(Utils.colorize("&cThe specified type doesn't exist!"));
            return;
        }

        Core.getInstance().getSettings().set("npcs."+args[1]+".attributes.type", args[3]);





    }
}
